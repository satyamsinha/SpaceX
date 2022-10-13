package com.example.spacex.launch.data

import com.example.spacex.common.data.repository.BaseRepository
import com.example.spacex.common.network.Resource
import com.example.spacex.launch.data.model.RocketRequest
import com.example.spacex.launch.domain.model.Docs
import com.example.spacex.launch.domain.model.Rocket
import kotlinx.coroutines.flow.Flow

class LaunchRepository(
    private val launchesRemoteDataSource: LaunchRemoteDataSource,
) : BaseRepository() {

    suspend fun getRocketList(): Flow<Resource<List<Rocket>>> = toResourceFlow(
        response = launchesRemoteDataSource.getRocketList(),
        transform = LaunchMapper :: mapToRocketList,
    )

    suspend fun getRocketDetailsById(rocketRequest : RocketRequest): Flow<Resource<Docs>> = toResourceFlow(
        response = launchesRemoteDataSource.getRocketDetailsById(rocketRequest),
        transform = LaunchMapper :: mapToLaunches,
    )
}