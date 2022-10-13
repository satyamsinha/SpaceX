package com.example.spacex.launch.data

import com.example.spacex.launch.data.model.*
import com.example.spacex.launch.domain.model.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object LaunchMapper {

    fun mapToRocketList(rocketListDto: List<RocketDto>) = rocketListDto.map {
        Rocket(
            name = it.name,
            id = it.id,
            country = it.country,
            flickr_images = it.flickr_images,
            engines = it.engines,
            description = it.description
        )
    }

    fun mapToLaunches(docsDto : DocsDto) = docsDto.run {
        Docs (
            launchList = getLaunchList(docsDto.docs)
        )
    }

    private fun getLaunchList (launchDtoList: List<LaunchDto>) = launchDtoList.map {

        val x = it.date_local?.split("T")
        val secondApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(x?.get(0) ?: "", secondApiFormat)
                    Launch (
                        name = it.name!!,
                        date_local = it.date_local!!,
                        success = it.success,
                        year = date.year,
                        links = it.linksDto
                    )
    }

}