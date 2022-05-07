package co.fullstacklabs.androidkotlinchallenge.ui.nodes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.fullstacklabs.androidkotlinchallenge.base.BaseViewModel
import co.fullstacklabs.androidkotlinchallenge.domain.model.NodeModel
import co.fullstacklabs.androidkotlinchallenge.domain.usecase.FetchNodeBlockUseCase
import co.fullstacklabs.androidkotlinchallenge.domain.usecase.FetchNodeStatusUseCase
import co.fullstacklabs.androidkotlinchallenge.extensions.toLiveData
import co.fullstacklabs.androidkotlinchallenge.store.NodesContainer
import kotlinx.coroutines.launch
import timber.log.Timber

internal class NodesViewModel(
    private val fetchNodeStatusUseCase: FetchNodeStatusUseCase,
    private val fetchNodeBlockUseCase: FetchNodeBlockUseCase,
): BaseViewModel() {
    private val _nodes = MutableLiveData<List<NodeModel>>()
    val nodes = _nodes.toLiveData()

    init {
        fetchNodeList()
    }

    private fun fetchNodeList() {
        _nodes.value = NodesContainer.getNodeList()

        nodes.value?.forEach { node ->
            viewModelScope.launch {
                runCatching {
                    val params = FetchNodeStatusUseCase.Params(url = node.url)
                    val result = fetchNodeStatusUseCase.execute(params)

                    updateNode(newNode = node.copy(online = true, name = result.name))
                }.recoverCatching {
                    updateNode(newNode = node.copy(online = false))
                }
            }
        }
    }

    private fun updateNode(newNode: NodeModel) {
        val index = _nodes.value?.indexOfFirst { it.url == newNode.url } ?: 0
        val newList = _nodes.value?.filter{ it.url != newNode.url }?.toMutableList()
        newList?.add(index, newNode)

        _nodes.value = newList?.toList()

        _nodes.value?.forEach { node->
            viewModelScope.launch {
                kotlin.runCatching {
                    val params = FetchNodeBlockUseCase.Params(url= node.url)
                    val result = fetchNodeBlockUseCase.execute(params)
                    node.block = result
                    Log.i("RUDDA","result: "+result.toString())
                }.recoverCatching {
                    Log.i("RUDDA","errp: "+it.message.toString())
                }
            }
        }

    }

    fun expandNode(node: NodeModel) {
        _nodes.value = _nodes.value?.map {
            if (it.url == node.url) {
                it.copy(expanded = node.expanded.not())
            } else {
                it.copy(expanded = false)
            }
        }
    }
}