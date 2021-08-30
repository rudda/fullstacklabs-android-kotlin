package co.fullstacklabs.androidkotlinchallenge.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface BaseUseCase<Params, out T> {
    suspend fun execute(params: Params? = null): T {
        return withContext(Dispatchers.IO) {
            run(params)
        }
    }

    suspend fun run (params: Params? = null): T
}