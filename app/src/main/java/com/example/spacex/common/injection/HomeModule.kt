package com.example.spacex.common.injection

import com.example.spacex.launch.data.LaunchRemoteDataSource
import com.example.spacex.launch.data.LaunchRepository
import com.example.spacex.launch.domain.GetRocketDataUseCase
import com.example.spacex.launch.domain.GetRocketListDetailUseCase
import com.example.spacex.launch.domain.model.Rocket
import com.example.spacex.launch.ui.launches.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val homeModule = module {
    viewModel {
        MainActivityViewModel(
            rocketListUseCase = get(),
            rocketDataUseCase = get()
        )
    }
    single { GetRocketListDetailUseCase(launchRepository = get()) }
    single { GetRocketDataUseCase(launchRepository = get()) }
    single { LaunchRepository(launchesRemoteDataSource = provideRemoteDataSource(retrofit = get())) }
}

fun provideRemoteDataSource(retrofit: Retrofit): LaunchRemoteDataSource =
    retrofit.create(LaunchRemoteDataSource::class.java)