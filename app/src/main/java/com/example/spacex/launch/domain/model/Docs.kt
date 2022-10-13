package com.example.spacex.launch.domain.model

import com.example.spacex.launch.data.model.LinksDto

data class Docs(
    val launchList: List<Launch?>
)
data class Launch(
    val name: String,
    val success: Boolean,
    //val launch_year: String?,
    val date_local: String?,
    var year: Int?,
    var links: LinksDto?
)


