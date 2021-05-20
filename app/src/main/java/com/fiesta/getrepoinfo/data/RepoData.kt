package com.fiesta.getrepoinfo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo_table")
data class RepoData (
    @PrimaryKey val sha: String,
    val message: String,
    val repoId: Int,
    var author: String,
    var url: String
)
