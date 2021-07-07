package com.dicoding.estomihi.githubsearchuser.database

import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val AUTHORITY = "com.dicoding.githubsearchuser"
    const val SCHEME = "content"

    internal class FavoriteColumns : BaseColumns {
        companion object {
            const val  TABLE_NAME = "Favorite"
            const val  _ID = "id"
            const val USERNAME = "username"
            const val AVATAR = "avatar"
            const val URL = "url"
            const val DATE = "date"

            val CONTENT_URI : Uri = Uri.Builder().scheme(SCHEME)
                    .authority(AUTHORITY)
                    .appendPath(TABLE_NAME)
                    .build()
        }
    }
    fun getColumnInt(cursor: Cursor?, columnName: String?): Int {
        return cursor?.getInt(cursor.getColumnIndex(columnName))?:0
    }
    fun getColumnString(cursor: Cursor?, columnName: String?): String{
        return cursor?.getString(cursor.getColumnIndex(columnName))?: ""
    }
}