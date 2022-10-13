package com.example.spacex.launch.data.model


data class DocsDto(
    val docs: List<LaunchDto>
)
data class LaunchDto(
    val name: String?,
    val success: Boolean,
    val date_local: String?,
    var linksDto : LinksDto
)
data class LinksDto (
    val flickr: FlickrDto,
    val patch : PatchDto
)
data class FlickrDto (
    val originalDto : List<String>
)
data class PatchDto (
    val large : String
)