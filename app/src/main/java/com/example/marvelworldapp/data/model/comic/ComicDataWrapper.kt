package com.example.marvelworldapp.data.model.comic

data class ComicDataWrapper(
    val code: Int,
    val status: String,
    val data: ComicDataContainer
)