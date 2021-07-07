package com.dicoding.estomihi.thegithubuserapp.data

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteUserDao {
    @Insert
    suspend fun addUserToFavorite(favoriteUser: UserData)

    @Query("SELECT * FROM favorites")
    fun getFavUser(): LiveData<List<UserData>>

    @Query("SELECT count(*) FROM favorites WHERE favorites.id = :id")
    suspend fun checkUserId(id: Int) : Int

    @Query("DELETE FROM favorites WHERE favorites.id = :id")
    suspend fun removeUserFavorite(id: Int) : Int

    @Query("SELECT * FROM favorites")
    fun getFavUserProvider(): Cursor
}