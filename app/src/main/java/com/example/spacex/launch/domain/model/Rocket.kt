package com.example.spacex.launch.domain.model

import com.example.spacex.launch.data.model.EngineDto

data class Rocket(
    val id: String,
    val engines: EngineDto,
    val flickr_images: List <String>,
    val name: String,
    val country: String,
    val description : String
)
data class Engines (
        val  number : Int
        )
