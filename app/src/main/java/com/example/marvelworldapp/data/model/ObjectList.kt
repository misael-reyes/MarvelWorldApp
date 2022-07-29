package com.example.marvelworldapp.data.model

import kotlin.collections.List

/**
 * clase objto que representa una lista de entidades resumidas, por ejemplo:
 * comics resumida
 * series resumida
 * personajes resumida
 */
data class ObjectList(
    val collectionURI: String, // este atributo nos permitira conocer los comics o series del personaje seleccionado
    val items: List<ObjectSummary>
)