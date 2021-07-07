package com.dicoding.estomihi.myfinaljetpack.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "favorite_movie")
@Parcelize
data class MovieEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int = 0,

    @NonNull
    @ColumnInfo(name = "movieTitle")
    var title: String? = null,

    @NonNull
    @ColumnInfo(name = "movieOverview")
    var overview: String? = null,

    @NonNull
    @ColumnInfo(name = "moviePoster")
    var posterPath: String? = null,

    @NonNull
    @ColumnInfo(name = "movieBackdrop")
    var backdropPath: String? = null,

    @NonNull
    @ColumnInfo(name = "movieVote")
    var voteAverage: Double? = null,

    @NonNull
    @ColumnInfo(name = "movieIsFavorite")
    var isFavorite: Boolean = false
): Parcelable