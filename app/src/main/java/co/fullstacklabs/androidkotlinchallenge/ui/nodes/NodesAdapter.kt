package co.fullstacklabs.androidkotlinchallenge.ui.nodes

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.fullstacklabs.androidkotlinchallenge.R
import co.fullstacklabs.androidkotlinchallenge.base.BaseViewHolder
import co.fullstacklabs.androidkotlinchallenge.databinding.ItemBlockBinding
import co.fullstacklabs.androidkotlinchallenge.databinding.ItemNodeBinding
import co.fullstacklabs.androidkotlinchallenge.domain.model.NodeBlockModel
import co.fullstacklabs.androidkotlinchallenge.domain.model.NodeModel

internal class NodesAdapter: RecyclerView.Adapter<BaseViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(model: NodeModel)
    }

    private var onClick: OnItemClickListener? = null

    fun setOnItemClickListener(clickListener: OnItemClickListener) {
        this.onClick = clickListener
    }

    var data: List<DataItem> = emptyList()
        set(value) {
            val result =  DiffUtil.calculateDiff(NodesDiffCallback(field, value))
            field = value
            result.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            R.layout.item_node -> {
                NodeViewHolder(
                    ItemNodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            R.layout.item_block -> {
                NodeBlockViewHolder(
                    ItemBlockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            else -> throw IllegalArgumentException("Unexpected viewType received: $viewType.")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is NodeViewHolder -> {
                val myNode = data[position] as DataItem.NodeItem
                holder.binding(node = myNode.nodeModule)
                holder.itemView.setOnClickListener {
                    onClick?.onItemClick(myNode.nodeModule)
                }
                holder.viewBinding.rvBlock.adapter = NodesAdapter().apply {

                    val myList  = mutableListOf<NodesAdapter.DataItem>()

                    myNode.nodeModule.block.forEach { block ->
                        myList.add( DataItem.BlockItem(block))
                    }
                    Log.i("RUDDA",  myList.size.toString())
                    data = myList
                    notifyDataSetChanged()
                }
            }
            is NodeBlockViewHolder -> {
                val myBlock = data[position] as DataItem.BlockItem
                holder.binding(myBlock.nodeBlock)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return when(data[position]){
            is DataItem.NodeItem -> {
                R.layout.item_node.toLong()
            }

            is DataItem.BlockItem -> {
                R.layout.item_block.toLong()

            }
            else -> 0L
        }

    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int = getItemId(position).toInt()

    private class NodesDiffCallback(
        private val oldData: List<DataItem>,
        private val newData: List<DataItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldData.size

        override fun getNewListSize(): Int = newData.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return try {
                when (val old = oldData[oldItemPosition]) {
                    is DataItem.NodeItem -> {
                        val new = newData[newItemPosition] as DataItem.NodeItem
                        old.nodeModule.url == new.nodeModule.url &&
                                old.nodeModule.block.size != new.nodeModule.block.size
                    }

                    is DataItem.BlockItem -> {
                        val new = newData[newItemPosition] as DataItem.BlockItem
                        old.nodeBlock.data == new.nodeBlock.data
                    }
                    else -> false
                }
            } catch (exception : ClassCastException) {
                true
            }
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData[oldItemPosition] == newData[newItemPosition]
        }
    }

     abstract class  DataItem(){
         data class NodeItem( val nodeModule : NodeModel) : DataItem()
         data class BlockItem( val nodeBlock : NodeBlockModel): DataItem()
     }
}