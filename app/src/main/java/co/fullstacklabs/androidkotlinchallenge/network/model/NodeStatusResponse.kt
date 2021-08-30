package co.fullstacklabs.androidkotlinchallenge.network.model

import co.fullstacklabs.androidkotlinchallenge.domain.model.NodeStatusModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class NodeStatusResponse(
    @Json(name = "node_name")
    val nodeName: String
)

internal fun NodeStatusResponse.toDomain(): NodeStatusModel {
    return NodeStatusModel(name = nodeName)
}