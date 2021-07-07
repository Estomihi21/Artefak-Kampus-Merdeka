package com.dicoding.estomihi.myfavorite.database

import android.database.Cursor
import com.dicoding.estomihi.myfavorite.data.UserData

object MappingHelper {
    fun mapCursorToArrayList(cursor: Cursor?) : ArrayList<UserData> {
        val listUsers = ArrayList<UserData>()

        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.USER_ID))
                val username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.USER_USERNAME))
                val urlAvatar = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.USER_AVATAR_URL))

                listUsers.add(
                    UserData(id,username,urlAvatar)
                )
            }
        }
        return listUsers
    }
}