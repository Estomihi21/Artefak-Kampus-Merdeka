package com.dicoding.estomihi.githubsearchuser.Data

data class  UserData(
    var id          : Int? = 0,
    var name        : String? = null,
    var username    : String? = null,
    var login       : String? = null,
    var location    : String? = null,
    var company     : String? = null,
    var repository  : Int? = 0,
    var followers   : Int? = 0,
    var following   : Int? = 0,
    var avatar      : String? = null,
    var url         : String? = null

)
