package com.example.spacex.launch.domain

import com.example.spacex.launch.data.LaunchRepository
import com.example.spacex.launch.data.model.RocketRequest

class GetRocketDataUseCase (private val launchRepository: LaunchRepository) {

    suspend operator fun invoke(id: RocketRequest) = launchRepository.getRocketDetailsById( id)
}
