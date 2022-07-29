package com.example.marvelworldapp.data.model.character

/**
 * clase rais de la respuesta que arroja la api al solicitar a todos
 * los personajes
 */
data class CharacterDataWrapper(
    val code: Int,
    val status: String,
    val data: CharacterDataContainer // datos que retorna la llamada
)