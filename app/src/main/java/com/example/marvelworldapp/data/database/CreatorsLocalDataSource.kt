package com.example.marvelworldapp.data.database

import com.example.marvelworldapp.data.database.dao.CreatorDao
import com.example.marvelworldapp.data.database.entities.CreatorEntity
import com.example.marvelworldapp.data.database.entities.SeriesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreatorsLocalDataSource @Inject constructor(
    private val creatorDao: CreatorDao
) {

    suspend fun getAllCreators(): List<CreatorEntity> {
        return withContext(Dispatchers.IO) {
            creatorDao.getAllCreators()
        }
    }

    suspend fun insertAllCreators(list: List<CreatorEntity>) {
        withContext(Dispatchers.IO) {
            creatorDao.insertAllCreators(list)
        }
    }

    suspend fun deleteAllCreators() {
        withContext(Dispatchers.IO) {
            creatorDao.deleteAllCreators()
        }
    }

    suspend fun getCreatorById(id_creator: Int): CreatorEntity {
        return withContext(Dispatchers.IO) {
            creatorDao.getCreatorById(id_creator)
        }
    }
}