package com.dicoding.estomihi.thegithubuserapp.data

import com.dicoding.estomihi.thegithubuserapp.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPointInterface {

    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getUsers(@Query("q") query: String): Call<ResponseData>

    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getUserDetail(@Path("username") username: String): Call<UserDetailData>

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getFollowers(@Path("username") username: String): Call<ArrayList<UserData>>

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getFollowing(@Path("username") username: String): Call<ArrayList<UserData>>

}