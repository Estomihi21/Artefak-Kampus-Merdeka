package com.dicoding.estomihi.mymovies.data.source.remote.response

import com.google.gson.annotations.SerializedName

class TvShowResponse (
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var title: String? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,
    @SerializedName("vote_average")
    var voteAverage: Double? = null
)