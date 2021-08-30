package co.fullstacklabs.androidkotlinchallenge.ui.nodes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.fullstacklabs.androidkotlinchallenge.R
import co.fullstacklabs.androidkotlinchallenge.base.BaseViewHolder
import co.fullstacklabs.androidkotlinchallenge.databinding.ItemNodeBinding
import co.fullstacklabs.androidkotlinchallenge.domain.model.NodeModel

internal class NodesAdapter: RecyclerView.Adapter<BaseViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(model: NodeModel)
    }

    private var onClick: OnItemClickListener? = null

    fun setOnItemClickListener(clickListener: OnItemClickListener) {
        this.onClick = clickListener
    }

    var data: List<NodeModel> = emptyList()
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
            else -> throw IllegalArgumentException("Unexpected viewType received: $viewType.")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is NodeViewHolder -> {
                holder.binding(node = data[position])
                holder.itemView.setOnClickListener {
                    onClick?.onItemClick(data[position])
                }
            }
        }
    }

    override fun getItemId(position: Int): Long {
        val item = data[position]
        return item.id.toLong()
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int = R.layout.item_node

    private class NodesDiffCallback(
        private val oldData: List<NodeModel>,
        private val newData: List<NodeModel>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldData.size

        override fun getNewListSize(): Int = newData.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData[oldItemPosition].url == newData[newItemPosition].url
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData[oldItemPosition] == newData[newItemPosition]
        }
    }
}