package com.fiesta.getrepoinfo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

//@Entity(tableName = "commit_table")
//@Parcelize
data class Info (
        val sha: String,
        val message: String,
        val commit: Commit
//    @PrimaryKey( autoGenerate = true) val id: Int = 0
//) : Parcelable
)