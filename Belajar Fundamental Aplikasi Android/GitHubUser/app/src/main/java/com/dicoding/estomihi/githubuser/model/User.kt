package com.dicoding.estomihi.githubuser.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class User(
    var avatar: Int,
    var username: String,
    var name: String,
    var company: String,
    var location: String,
    var repository: String,
    var follower: String,
    var following: String

) : Parcelable
