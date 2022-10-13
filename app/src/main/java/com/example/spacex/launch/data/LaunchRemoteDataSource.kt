package com.example.spacex.launch.data

import com.example.spacex.launch.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LaunchRemoteDataSource {

    @GET("v4/rockets")
    suspend fun getRocketList(): Response<List<RocketDto>>

    @POST("v4/launches/query")
    suspend fun getRocketDetailsById(@Body  rocketRequest: RocketRequest ): Response<DocsDto>
}