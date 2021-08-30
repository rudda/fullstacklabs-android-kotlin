package co.fullstacklabs.androidkotlinchallenge.di

import co.fullstacklabs.androidkotlinchallenge.base.RetrofitBuilder
import co.fullstacklabs.androidkotlinchallenge.network.NodesNetwork
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single(qualifier = ApiNodesQualifier) { RetrofitBuilder().build() }

    single {
        get<Retrofit>(qualifier = ApiNodesQualifier).create(NodesNetwork::class.java)
    }
}