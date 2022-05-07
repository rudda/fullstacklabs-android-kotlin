package co.fullstacklabs.androidkotlinchallenge.network.model

import co.fullstacklabs.androidkotlinchallenge.domain.model.NodeBlockModel
import co.fullstacklabs.androidkotlinchallenge.domain.model.NodeStatusModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class NodeBlockResponse(
    @Json(name = "data")
    val data: List<NodeBlock>
)

@JsonClass(generateAdapter = true)
internal data class NodeBlock(
    @Json(name = "attributes")
    val attribute: NodeBlockAttributes,
)

@JsonClass(generateAdapter = true)
internal data class NodeBlockAttributes(
    @Json(name = "index")
    val index: Int,
    @Json(name = "data")
    val data : String
)
//its cool
internal fun NodeBlockResponse.toDomain() =
    data.map {
        NodeBlockModel(it.attribute.index, it.attribute.data)
    }
