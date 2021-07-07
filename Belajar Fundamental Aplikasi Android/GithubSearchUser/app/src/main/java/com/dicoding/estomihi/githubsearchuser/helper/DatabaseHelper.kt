package com.dicoding.estomihi.githubsearchuser.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dicoding.estomihi.githubsearchuser.database.DatabaseContract
import com.dicoding.estomihi.githubsearchuser.database.DatabaseContract.FavoriteColumns.Companion.TABLE_NAME

internal class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "GithubUser"
        private const val DATABASE_VERSION = 1

        private const val SQL_CREATE_TABLE_NOTE = "CREATE TABLE $TABLE_NAME" +
                "(${DatabaseContract.FavoriteColumns._ID} INT PRIMARY KEY," +
                "${DatabaseContract.FavoriteColumns.USERNAME} TEXT NOT NULL UNIQUE" +
                "${DatabaseContract.FavoriteColumns.AVATAR} TEXT NOT NULL" +
                "${DatabaseContract.FavoriteColumns.URL} TEXT NOT NULL" +
                "${DatabaseContract.FavoriteColumns.DATE} TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_NOTE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}