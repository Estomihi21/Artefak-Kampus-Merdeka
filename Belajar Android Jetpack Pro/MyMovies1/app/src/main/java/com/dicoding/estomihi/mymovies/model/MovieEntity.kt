package com.dicoding.estomihi.mymovies.model

data class MovieEntity (
    var id: String,
    var title: String,
    var imgPoster: Int,
    var descStory: String,
    var nameDirector: String,
    var rating: Double,
    var genre: String,
)