package com.example.marvelworldapp.data.network

import com.example.marvelworldapp.data.model.character.CharacterDataWrapper
import com.example.marvelworldapp.data.model.comic.ComicDataWrapper
import com.example.marvelworldapp.data.model.creator.CreatorDataWrapper
import com.example.marvelworldapp.data.model.series.SeriesDataWrapper
import com.example.marvelworldapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * interfaz para definir todas las rutas a la api
 *
 * la API de marvel sugiere que mandemos como parametro un apikey, ts y hash
 * en cada petición
 */
interface ApiClient {

    // PETICIONES PARA LOS PERSONAJES

    /**
     * petición para retornar a un personaje
     */
    @GET("v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: Int,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash()
    ) : CharacterDataWrapper

    /**
     * petición para retornar a todos los personajes
     */
    @GET("v1/public/characters")
    suspend fun getAllCharacters(
        @Query("limit") limit: Int = 100,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash()
    ): CharacterDataWrapper

    /**
     * petición para retornar a los personajes de de un comic
     */
    @GET("v1/public/comics/{comicId}/characters")
    suspend fun getCharactersOfComic(
        @Path("comicId") comicId: Int,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash()
    ): CharacterDataWrapper

    /**
     * peticion para retornar a los personajes de una serie
     */
    @GET("v1/public/series/{seriesId}/characters")
    suspend fun getCharactersOfSeries(
        @Path("seriesId") seriesId: Int,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash()
    ): CharacterDataWrapper


    // PETICIONES PARA LOS COMICS

    /**
     * peticion para retornar todos los comics
     */
    @GET("v1/public/comics")
    suspend fun getAllComics(
        @Query("limit") limit: Int = 100,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash()
    ): ComicDataWrapper

    /**
     * petición para retornar a los comics de un personaje
     */
    @GET("v1/public/characters/{characterId}/comics")
    suspend fun getComicsOfCharacter(
        @Path("characterId") characterId: Int,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash()
    ): ComicDataWrapper

    /**
     * petición para retornar los comics de una serie
     */
    @GET("v1/public/series/{seriesId}/comics")
    suspend fun getComicsOfSeries(
        @Path("seriesId") seriesId: Int,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash()
    ): ComicDataWrapper

    /**
     * petición para retornar a un comic
     */
    @GET("v1/public/comics/{comicId}")
    suspend fun getComicById(
        @Path("comicId") comicId: Int,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash()
    ): ComicDataWrapper


    // PETICIONES PARA LAS SERIES

    /**
     * peticion para retornar a todas las series
     */
    @GET("v1/public/series")
    suspend fun getAllSeries(
        @Query("limit") limit: Int = 100,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash()
    ): SeriesDataWrapper

    /**
     * petición para retornar las series de un personaje
     */
    @GET("v1/public/characters/{characterId}/series")
    suspend fun getSeriesOfCharacter(
        @Path("characterId") characterId: Int,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash()
    ) : SeriesDataWrapper

    /**
     * petición para retornar a una serie
     */
    @GET("v1/public/series/{seriesId}")
    suspend fun getSeriesById(
        @Path("seriesId") seriesId: Int,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash()
    ) : SeriesDataWrapper


    // PETICIONES PARA LOS CREADORES

    /**
     * petición para retornar a los creadores de un comic
     */
    @GET("v1/public/comics/{comicId}/creators")
    suspend fun getCreatorOfComic(
        @Path("comicId") comicId: Int,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash()
    ): CreatorDataWrapper

    /**
     * petcion para retornar a un personaje por su nombre
     */
    @GET("v1/public/characters")
    suspend fun getCharacterByName(
        @Query("nameStartsWith") nameStartsWith: String,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash()
    ) : CharacterDataWrapper
}