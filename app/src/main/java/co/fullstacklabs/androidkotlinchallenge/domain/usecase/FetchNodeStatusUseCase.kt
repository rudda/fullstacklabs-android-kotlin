package co.fullstacklabs.androidkotlinchallenge.domain.usecase

import co.fullstacklabs.androidkotlinchallenge.domain.BaseUseCase
import co.fullstacklabs.androidkotlinchallenge.domain.model.NodeStatusModel
import co.fullstacklabs.androidkotlinchallenge.repository.NodesRepository
import timber.log.Timber

internal class FetchNodeStatusUseCase(
    private val nodesRepository: NodesRepository
) : BaseUseCase<FetchNodeStatusUseCase.Params, NodeStatusModel> {

    data class Params(
        val url: String
    )

    override suspend fun run(params: Params?): NodeStatusModel = try {
        requireNotNull(params) {
            "Failed to load params."
        }

        nodesRepository.getNodeStatus(url = "${params.url}/api/v1/status")
    } catch (e: Exception) {
        throw UseCaseException(e.message, e).also {
            Timber.e(e, "Failed to load node status.")
        }
    }

    class UseCaseException @JvmOverloads constructor(
        message: String? = null,
        throwable: Throwable? = null
    ) : Exception(message, throwable)
}