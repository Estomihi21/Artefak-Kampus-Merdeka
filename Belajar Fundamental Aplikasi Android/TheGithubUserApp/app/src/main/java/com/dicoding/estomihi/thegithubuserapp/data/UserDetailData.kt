package com.dicoding.estomihi.thegithubuserapp.data

data class UserDetailData(
    val id: Int,
    val login: String,
    val avatar_url: String,
    val name: String,
    val company: String,
    val location: String,
    val bio: String,
    val public_repos: Int,
    val followers: Int,
    val following: Int,
    val html_url: String
)