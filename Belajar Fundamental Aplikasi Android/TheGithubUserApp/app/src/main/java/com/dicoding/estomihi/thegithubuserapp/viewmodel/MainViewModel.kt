package com.dicoding.estomihi.thegithubuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.estomihi.thegithubuserapp.MainActivity
import com.dicoding.estomihi.thegithubuserapp.data.ResponseData
import com.dicoding.estomihi.thegithubuserapp.data.Retrofit
import com.dicoding.estomihi.thegithubuserapp.data.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    val listUser = MutableLiveData<ArrayList<UserData>>()

    fun setSearchUser(name: String){
        Retrofit.apiRequest.getUsers(name).enqueue(object : Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                if (response.isSuccessful) {
                    listUser.postValue(response.body()?.items)
                    Log.d(TAG, response.body()?.items.toString())
                } else {
                    Log.d(TAG, response.code().toString())
                }
            }
            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Log.d("Fail To Fetching", t.message.toString())
            }

        })
    }

    fun getSearchUser() : LiveData<ArrayList<UserData>> {
        return listUser
    }
}