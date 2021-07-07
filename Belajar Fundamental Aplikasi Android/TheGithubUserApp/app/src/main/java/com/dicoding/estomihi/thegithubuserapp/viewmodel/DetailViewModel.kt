package com.dicoding.estomihi.thegithubuserapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.estomihi.thegithubuserapp.data.FavoriteUserDao
import com.dicoding.estomihi.thegithubuserapp.data.Retrofit
import com.dicoding.estomihi.thegithubuserapp.data.UserData
import com.dicoding.estomihi.thegithubuserapp.data.UserDetailData
import com.dicoding.estomihi.thegithubuserapp.database.UserRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel (application: Application) : AndroidViewModel(application) {
    val detailUser = MutableLiveData<UserDetailData>()
    val listFollowersUser = MutableLiveData<ArrayList<UserData>>()
    val listFollowingUser = MutableLiveData<ArrayList<UserData>>()

    private var userFavDao: FavoriteUserDao?
    private var userFavDb: UserRoomDatabase? = UserRoomDatabase.getDb(application)

    init {
        userFavDao = userFavDb?.favoriteUserDao()
    }

    fun setDetailUser(username: String) {
        Retrofit.apiRequest.getUserDetail(username).enqueue(object : Callback<UserDetailData> {

            override fun onResponse(call: Call<UserDetailData>, response: Response<UserDetailData>
            ) {
                if (response.isSuccessful) {
                    detailUser.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<UserDetailData>, t: Throwable) {
                Log.d("Fail To Fetching", t.message.toString())
            }

        })
    }

    fun getDetailUser(): LiveData<UserDetailData> {
        return detailUser
    }

    fun setFollowersUser(username: String) {
        Retrofit.apiRequest.getFollowers(username).enqueue(object : Callback<ArrayList<UserData>> {

            override fun onResponse(call: Call<ArrayList<UserData>>, response: Response<ArrayList<UserData>>) {
                if (response.isSuccessful) {
                    listFollowersUser.postValue((response.body()))
                }
            }

            override fun onFailure(call: Call<ArrayList<UserData>>, t: Throwable) {
                Log.d("Fail To Fetching", t.message.toString())
            }

        })
    }

    fun getFollowersUser(): LiveData<ArrayList<UserData>> {
        return listFollowersUser
    }

    fun setFollowingUser(username: String) {
        Retrofit.apiRequest.getFollowing(username).enqueue(object : Callback<ArrayList<UserData>> {

            override fun onResponse(call: Call<ArrayList<UserData>>, response: Response<ArrayList<UserData>>) {
                if (response.isSuccessful) {
                    listFollowingUser.postValue((response.body()))
                }
            }

            override fun onFailure(call: Call<ArrayList<UserData>>, t: Throwable) {
                Log.d("Fail To Fetching", t.message.toString())
            }

        })
    }

    fun getFollowingUser(): LiveData<ArrayList<UserData>> {
        return listFollowingUser
    }

    fun addUserToFav(id: Int, username: String, avatarUrl: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val userFav = UserData(
                id,
                username,
                avatarUrl
            )
            userFavDao?.addUserToFavorite(userFav)
        }
    }

    suspend fun checkUserFavId(id: Int) = userFavDao?.checkUserId(id)

    fun deleteFromFav(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userFavDao?.removeUserFavorite(id)
        }
    }

    fun getFavoriteUser(): LiveData<List<UserData>>? {
        return userFavDao?.getFavUser()
    }
}