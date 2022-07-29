package com.example.marvelworldapp.utils

import com.example.marvelworldapp.data.model.character.CharacterDataWrapper
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

/**
 * clase en el que se alojarán las constentes que necesitenmso a lo largo del proyecto
 */
class Constants {
    companion object {
        const val BASE_URL = "https://gateway.marvel.com/"

        // time requerido por la documentación de marvel
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()

        // para obtener nuestras key, es necesario crear una cuenta en https://developer.marvel.com/
        const val API_KEY = "815f955e39b3e6b25878c2e4ec01d515"
        const val PRIVATE_KEY = "35f6c6f335a2596a284416fa2d12affadd5ab7cf"

        const val limit = "20"

        // función para crear el hash que nos solicita la documentación de marvel
        fun hash(): String {
            val input = "$timeStamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }
    }
}