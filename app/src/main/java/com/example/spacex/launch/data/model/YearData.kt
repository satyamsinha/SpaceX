package com.example.spacex.launch.data.model

import com.example.spacex.launch.domain.model.Launch

data class YearData(
    val yearTitle : String,
    var launchList: List <Launch>
)
