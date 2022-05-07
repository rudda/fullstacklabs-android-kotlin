package co.fullstacklabs.androidkotlinchallenge.repository

import co.fullstacklabs.androidkotlinchallenge.domain.model.NodeBlockModel
import co.fullstacklabs.androidkotlinchallenge.domain.model.NodeStatusModel

interface NodesRepository {
    suspend fun getNodeStatus(url: String): NodeStatusModel
    suspend fun getBlock(url: String): List<NodeBlockModel>
}