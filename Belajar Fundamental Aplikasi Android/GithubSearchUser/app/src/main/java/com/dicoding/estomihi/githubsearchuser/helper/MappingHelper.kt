package com.dicoding.estomihi.githubsearchuser.helper

import android.database.Cursor
import com.dicoding.estomihi.githubsearchuser.Data.UserData
import com.dicoding.estomihi.githubsearchuser.database.DatabaseContract

object MappingHelper {
    fun mapCursorToArrayList(usersCursor : Cursor?) : ArrayList<UserData>{
        val userlist = ArrayList<UserData>()

        usersCursor?.apply {
            while (moveToNext()){
                val id       = getInt(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns._ID))
                val username = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.USERNAME))
                val avatar   = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.AVATAR))
                val url      = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.URL))

                userlist.add(UserData(id, username, avatar, url))
            }
        }
        return userlist
    }
}