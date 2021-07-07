package com.dicoding.estomihi.thegithubuserapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.estomihi.thegithubuserapp.data.FavoriteUserDao
import com.dicoding.estomihi.thegithubuserapp.data.UserData

@Database(
    entities = [UserData::class],
    version = 1
)
abstract class UserRoomDatabase : RoomDatabase(){
    companion object{
        var INSTANCE: UserRoomDatabase? = null

        fun getDb(context: Context) : UserRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(UserRoomDatabase::class){
                    INSTANCE =
                        Room.databaseBuilder(context.applicationContext, UserRoomDatabase::class.java, "user_database").build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun favoriteUserDao(): FavoriteUserDao
}