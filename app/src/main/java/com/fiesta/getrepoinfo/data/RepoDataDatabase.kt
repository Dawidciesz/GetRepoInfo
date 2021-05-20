package com.fiesta.getrepoinfo.data


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RepoData::class], version = 2)
abstract class RepoDataDatabase : RoomDatabase() {

    abstract fun repoDataDao(): RepoDataDao
}