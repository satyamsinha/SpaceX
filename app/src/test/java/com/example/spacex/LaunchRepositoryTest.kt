package com.example.spacex

import com.example.spacex.common.network.Resource
import com.example.spacex.launch.data.LaunchRepository
import com.example.spacex.launch.data.model.RocketDto
import com.example.spacex.launch.domain.model.Rocket
import junit.framework.Assert
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.Flow
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.Response


class LaunchRepositoryTest {

    @Mock
    lateinit var launchRepository : LaunchRepository

    @Mock
    lateinit var listLaunch : Flow<Resource<List<Rocket>>>

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        //launchRepository = LaunchRepository()


    }
    @Test
    open suspend fun testNull() {
        Mockito.`when`(launchRepository.getRocketList()).thenReturn(null)
        Assert.assertNotNull(launchRepository?.getRocketList())
        assertTrue(launchRepository?.getRocketList()==null)
    }

    @Test
    suspend fun testApiFetchDataSuccess() {
        // Mock API response
        `when`(launchRepository?.getRocketList()).thenReturn( listLaunch)
    }
}