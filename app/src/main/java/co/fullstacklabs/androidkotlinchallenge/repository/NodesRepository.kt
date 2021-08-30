package co.fullstacklabs.androidkotlinchallenge.repository

import co.fullstacklabs.androidkotlinchallenge.domain.model.NodeStatusModel

interface NodesRepository {
    suspend fun getNodeStatus(url: String): NodeStatusModel
}