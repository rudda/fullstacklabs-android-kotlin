package co.fullstacklabs.androidkotlinchallenge.network

import co.fullstacklabs.androidkotlinchallenge.network.model.NodeStatusResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

internal interface NodesNetwork {
    @GET
    suspend fun getNodeStatus(@Url url: String): Response<NodeStatusResponse>
}