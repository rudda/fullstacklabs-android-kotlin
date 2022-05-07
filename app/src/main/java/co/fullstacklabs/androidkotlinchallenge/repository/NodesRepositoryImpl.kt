package co.fullstacklabs.androidkotlinchallenge.repository

import co.fullstacklabs.androidkotlinchallenge.domain.model.NodeBlockModel
import co.fullstacklabs.androidkotlinchallenge.domain.model.NodeStatusModel
import co.fullstacklabs.androidkotlinchallenge.extensions.parseResponse
import co.fullstacklabs.androidkotlinchallenge.network.NodesNetwork
import co.fullstacklabs.androidkotlinchallenge.network.base.NetworkException
import co.fullstacklabs.androidkotlinchallenge.network.base.Outcome
import co.fullstacklabs.androidkotlinchallenge.network.model.toDomain

internal class NodesRepositoryImpl(
    private val network: NodesNetwork
) : NodesRepository {

    @Throws(NetworkException::class)
    override suspend fun getNodeStatus(url: String): NodeStatusModel {
        return when(val outcome = network.getNodeStatus(url).parseResponse()) {
            is Outcome.Success -> outcome.value.toDomain()
            is Outcome.Failure -> throw NetworkException.parse(outcome.statusCode)
        }
    }
    @Throws(NetworkException::class)
    override suspend fun getBlock(url: String): List<NodeBlockModel> {
        return when(val outcome = network.getNodeBlock(url).parseResponse()) {
            is Outcome.Success -> outcome.value.toDomain()
            is Outcome.Failure -> throw NetworkException.parse(outcome.statusCode)
        }
    }
}