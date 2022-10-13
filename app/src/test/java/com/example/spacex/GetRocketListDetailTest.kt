package com.example.spacex

import com.example.spacex.launch.domain.GetRocketListDetailUseCase
import junit.framework.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class GetRocketListDetailTest {

    @Mock
    val getRocketList : GetRocketListDetailUseCase = TODO()

    @Test
    open suspend fun testNull() {
        Mockito.`when`(getRocketList.invoke()).thenReturn(null)
        Assert.assertNotNull(getRocketList())
    }
}