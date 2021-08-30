package co.fullstacklabs.androidkotlinchallenge.network.base

data class ErrorResponse(
    val errorBody: String
) : BaseErrorResponse()