package com.dicoding.estomihi.mymovies.model

data class MovieEntity (
    var id: Int = 0,
    var title: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val voteAverage: Double? = null,
)