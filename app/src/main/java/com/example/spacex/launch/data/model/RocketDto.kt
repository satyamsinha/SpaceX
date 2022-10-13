package com.example.spacex.launch.data.model

data class RocketDto(
    val id : String,
    val engines : EngineDto,
    val flickr_images : List <String> ,
    val name : String,
    val country : String,
    val description : String
)
data class EngineDto (
        val  number : Int
        )
