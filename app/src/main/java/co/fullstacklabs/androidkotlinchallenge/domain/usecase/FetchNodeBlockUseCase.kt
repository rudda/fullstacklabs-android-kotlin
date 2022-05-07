package co.fullstacklabs.androidkotlinchallenge.domain.usecase

import co.fullstacklabs.androidkotlinchallenge.domain.BaseUseCase
import co.fullstacklabs.androidkotlinchallenge.domain.model.NodeBlockModel
import co.fullstacklabs.androidkotlinchallenge.domain.model.NodeStatusModel
import co.fullstacklabs.androidkotlinchallenge.repository.NodesRepository
import timber.log.Timber

class FetchNodeBlockUseCase(  private val nodesRepository: NodesRepository)
    : BaseUseCase<FetchNodeBlockUseCase.Params, List<NodeBlockModel>> {

    data class Params(
        val url: String
    )

    override suspend fun run(params: FetchNodeBlockUseCase.Params?): List<NodeBlockModel> = try{
        requireNotNull(params) {
            "Failed to load params."
        }

        nodesRepository.getBlock(url = "${params.url}/api/v1/blocks")
    } catch (e: Exception) {
        throw FetchNodeStatusUseCase.UseCaseException(e.message, e).also {
                Timber.e(e, "Failed to load node status.")
        }
    }
}