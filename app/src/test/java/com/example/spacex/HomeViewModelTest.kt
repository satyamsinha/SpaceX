package com.example.spacex

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.spacex.common.injection.homeModule
import com.example.spacex.launch.domain.GetRocketDataUseCase
import com.example.spacex.launch.domain.GetRocketListDetailUseCase
import com.example.spacex.launch.ui.launches.HomeUiState
import com.example.spacex.launch.ui.launches.LaunchUiState
import com.example.spacex.launch.ui.launches.MainActivityViewModel
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.spy
import org.mockito.MockitoAnnotations


class HomeViewModelTest {
   /* @Mock
    private lateinit var  rocketListUseCase : GetRocketListDetailUseCase

    @Mock
    private lateinit var rocketDataUseCase : GetRocketDataUseCase*/

    @Mock
    val homeUiState : HomeUiState? = null

    @Mock
    @InjectMocks
    private lateinit var viewModel: MainActivityViewModel

    @Mock
    var observer: Observer<MainActivityViewModel>? = null

    @Mock
    var liveDataHomeData = MutableLiveData<HomeUiState>()
    @Mock
    val liveDataLaunchData = MutableLiveData<LaunchUiState>()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel?.liveDataHomeData?.value = HomeUiState.Loader

        /*homeModule.ge
         viewModel = spy(rocketListUseCase?.let { rocketDataUseCase?.let { it1 ->
             MainActivityViewModel(it,
                 it1
             )
         } })*/
    }

    @Test
    open fun testNull() {
        `when`(viewModel?.fetchRocketListData()).thenReturn(null)
        assertNotNull(viewModel?.fetchRocketListData())
        //viewModel?.liveDataHomeData = null
    }


}