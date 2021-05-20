package com.fiesta.getrepoinfo.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDataDao {

    @Query("SELECT * FROM repo_table")
    fun getRepoData(): Flow<List<RepoData>>

    @Query("SELECT DISTINCT * FROM repo_table")
    fun getOneRepoData(): Flow<List<RepoData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(restaurants: List<RepoData>)

    @Query("DELETE FROM repo_table")
    suspend fun deleteAll()
}