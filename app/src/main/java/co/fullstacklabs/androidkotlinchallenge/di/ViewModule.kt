package co.fullstacklabs.androidkotlinchallenge.di

import co.fullstacklabs.androidkotlinchallenge.domain.usecase.FetchNodeBlockUseCase
import co.fullstacklabs.androidkotlinchallenge.domain.usecase.FetchNodeStatusUseCase
import co.fullstacklabs.androidkotlinchallenge.ui.nodes.NodesAdapter
import co.fullstacklabs.androidkotlinchallenge.ui.nodes.NodesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    factory { FetchNodeStatusUseCase(nodesRepository = get()) }
    factory { FetchNodeBlockUseCase(nodesRepository = get()) }
    factory { NodesAdapter() }

    viewModel { NodesViewModel(fetchNodeStatusUseCase = get(), fetchNodeBlockUseCase = get()) }
}