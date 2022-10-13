package com.example.spacex.launch.ui.launches

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacex.common.network.Resource
import com.example.spacex.launch.data.model.RocketRequest
import com.example.spacex.launch.data.model.Query
import com.example.spacex.launch.domain.GetRocketDataUseCase
import com.example.spacex.launch.domain.GetRocketListDetailUseCase
import com.example.spacex.launch.domain.model.Docs
import com.example.spacex.launch.domain.model.Rocket
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val rocketListUseCase : GetRocketListDetailUseCase,
    private val rocketDataUseCase: GetRocketDataUseCase
) : ViewModel() {

    var liveDataHomeData = MutableLiveData<HomeUiState>()
    val liveDataLaunchData = MutableLiveData<LaunchUiState>()

    fun fetchRocketListData() {
        liveDataHomeData.value = HomeUiState.Loader
        viewModelScope.launch {
            val rocketListResFlow = async { rocketListUseCase() }
            sendResult(
                rocketListResult = rocketListResFlow.await()
            )
        }
    }

    private suspend fun sendResult(
        rocketListResult: Flow<Resource<List<Rocket>>>
    ) {
        var hasData = false
        var rocketList: List<Rocket?> = listOf()

        rocketListResult.collect  {
            when (it) {
                is Resource.Success -> {
                    hasData = true
                    rocketList = it.data
                }
                is Resource.Failure -> {}
                is Resource.Error -> {}
            }
        }
        if (hasData)
            liveDataHomeData.value = mapToHomeUiState (
                  rocketList = rocketList as List<Rocket>
            )
    }


    fun fetchRocketData(id: String) {
        liveDataLaunchData.value = LaunchUiState.Loader
        viewModelScope.launch {
            val query = Query( id )
            val rocketRequest = RocketRequest(query)
            val rocketLaunchResFlow = async { rocketDataUseCase.invoke(rocketRequest) }
            sendLaunchResult(
                docsResult = rocketLaunchResFlow.await()
            )
        }
    }
    private suspend fun sendLaunchResult(
        docsResult: Flow<Resource<Docs>>
    ) {
        var hasData = false
        var docs = Docs(emptyList())
        docsResult.collect  {
            when (it) {
                is Resource.Success -> {
                    hasData = true
                    docs = it.data
                }
                is Resource.Failure -> {}
                is Resource.Error -> {}
            }
        }

        if (hasData)
            liveDataLaunchData.value = mapToHomeLaunchUiState (
                docs = docs
            )

    }
}
