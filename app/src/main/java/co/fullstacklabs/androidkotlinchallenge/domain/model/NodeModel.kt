package co.fullstacklabs.androidkotlinchallenge.domain.model

data class NodeModel(
    val id: Int,
    val url: String,
    val online: Boolean = false,
    val name: String = "",
    val loading: Boolean = false,
    val expanded: Boolean = false,
    var block : List<NodeBlockModel> = emptyList()
)