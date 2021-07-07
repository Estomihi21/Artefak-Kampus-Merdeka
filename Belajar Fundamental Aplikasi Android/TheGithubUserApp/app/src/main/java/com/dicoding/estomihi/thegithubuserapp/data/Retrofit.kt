package com.dicoding.estomihi.thegithubuserapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private const val BASE_URL = "https://api.github.com/"
    private val retrofitConnect: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiRequest: ApiEndPointInterface = retrofitConnect.create(ApiEndPointInterface::class.java)
}