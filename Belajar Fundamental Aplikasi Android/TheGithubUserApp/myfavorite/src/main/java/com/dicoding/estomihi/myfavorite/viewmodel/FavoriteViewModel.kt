package com.dicoding.estomihi.myfavorite.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.estomihi.myfavorite.data.UserData
import com.dicoding.estomihi.myfavorite.database.DatabaseContract
import com.dicoding.estomihi.myfavorite.database.MappingHelper

class FavoriteViewModel (application: Application) : AndroidViewModel(application) {

    private var listFavorite = MutableLiveData<ArrayList<UserData>>()

    fun setFavoriteUser(context: Context) {
        val cursor = context.contentResolver.query(
            DatabaseContract.FavoriteUserColumns.CONTENT_URI,
            null,
            null,
            null,
            null,
        )
        val listConvertToArrayList = MappingHelper.mapCursorToArrayList(cursor)
        listFavorite.postValue(listConvertToArrayList)
    }

    fun getFavoriteUser(): LiveData<ArrayList<UserData>> {
        return listFavorite
    }
}