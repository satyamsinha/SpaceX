package com.example.spacex.launch.ui.launches

import com.example.spacex.launch.domain.model.Docs
import com.example.spacex.launch.domain.model.Launch
import com.example.spacex.launch.domain.model.Rocket

sealed class HomeUiState {
    class Success(
        val rocketList: List<Rocket> = listOf() ,
    ) : HomeUiState()
    data class Failure(val message: String) : HomeUiState()
    object Loader : HomeUiState()
}

fun mapToHomeUiState(rocketList: List<Rocket>) =
    HomeUiState.Success(
        rocketList = rocketList ,
    )
fun mapToHomeLaunchUiState(docs: Docs) =
    LaunchUiState.Success(
        docs = docs ,
    )
sealed class LaunchUiState {
    class Success(
        val docs: Docs  ,
    ) : LaunchUiState()
    data class Failure(val message: String) : LaunchUiState()
    object Loader : LaunchUiState()
}

