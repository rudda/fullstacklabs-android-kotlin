package co.fullstacklabs.androidkotlinchallenge.di

import co.fullstacklabs.androidkotlinchallenge.repository.NodesRepository
import co.fullstacklabs.androidkotlinchallenge.repository.NodesRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<NodesRepository> { NodesRepositoryImpl(network = get()) }
}