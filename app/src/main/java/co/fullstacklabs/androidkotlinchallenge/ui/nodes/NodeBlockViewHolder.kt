package co.fullstacklabs.androidkotlinchallenge.ui.nodes

import co.fullstacklabs.androidkotlinchallenge.base.BaseViewHolder
import co.fullstacklabs.androidkotlinchallenge.databinding.ItemBlockBinding
import co.fullstacklabs.androidkotlinchallenge.databinding.ItemNodeBinding
import co.fullstacklabs.androidkotlinchallenge.domain.model.NodeBlockModel

internal class NodeBlockViewHolder (private val viewBinding: ItemBlockBinding):
    BaseViewHolder(viewBinding) {

        fun binding(block : NodeBlockModel){
            viewBinding.tvHeader.text = block.index.toString().padStart(3, '0')
            viewBinding.blockBody.text = block.data
        }
    }