package com.dicoding.estomihi.thegithubuserapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favorites")
data class UserData(
    @PrimaryKey
    val  id       : Int,
    val login     : String,
    val avatar_url: String
) : Serializable