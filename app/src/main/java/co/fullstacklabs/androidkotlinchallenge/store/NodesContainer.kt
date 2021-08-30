package co.fullstacklabs.androidkotlinchallenge.store

import co.fullstacklabs.androidkotlinchallenge.domain.model.NodeModel

class NodesContainer {
    companion object {
        private val list = listOf(
            NodeModel(
                id = 1,
                name = "Node 1",
                url = "https://thawing-springs-53971.herokuapp.com",
            ),
            NodeModel(
                id = 2,
                name = "Node 2",
                url = "https://secret-lowlands-62331.herokuapp.com",
            ),
            NodeModel(
                id = 3,
                name = "Node 3",
                url = "https://calm-anchorage-82141.herokuapp.com",
            ),
            NodeModel(
                id = 4,
                name = "Node 4",
                url = "http://localhost:3002",
            )
        )

        fun getNodeList(): List<NodeModel> {
            return list
        }
    }
}