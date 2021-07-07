package com.dicoding.estomihi.myfavorite.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class UserData (
    @PrimaryKey
    val id: Int,
    val login: String,
    val avatar_url: String
        )