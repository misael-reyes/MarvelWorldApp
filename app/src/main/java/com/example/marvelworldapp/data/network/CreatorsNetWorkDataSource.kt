package com.example.marvelworldapp.data.network

import com.example.marvelworldapp.data.model.creator.CreatorDataWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreatorsNetWorkDataSource @Inject constructor(
    private val api: ApiClient
) {

    suspend fun getCreatorsOfComic(idComic: Int): CreatorDataWrapper {
        return withContext(Dispatchers.IO) {
            api.getCreatorOfComic(idComic)
        }
    }
}