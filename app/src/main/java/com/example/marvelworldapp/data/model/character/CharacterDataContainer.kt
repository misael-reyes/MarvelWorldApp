package com.example.marvelworldapp.data.model.character

import kotlin.collections.List

/**
 * clase objeto que contiene la lista de los personajes devueltos
 * por la llamada
 */
data class CharacterDataContainer(
    val results: List<Character>
)