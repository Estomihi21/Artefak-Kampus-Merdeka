package com.dicoding.estomihi.myfinaljetpack.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "favorite_tvShow")
@Parcelize
data class TvShowEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @NonNull
    @ColumnInfo(name = "tvShowId")
    var tvShowId: Int = 0,

    @NonNull
    @ColumnInfo(name = "tvShowTitle")
    var title: String? = null,

    @NonNull
    @ColumnInfo(name = "tvShowOverview")
    var overview: String? = null,

    @NonNull
    @ColumnInfo(name = "tvShowPoster")
    var posterPath: String? = null,

    @NonNull
    @ColumnInfo(name = "tvShowBackdrop")
    var backdropPath: String? = null,

    @NonNull
    @ColumnInfo(name = "tvShowVote")
    var voteAverage: Double? = null,

    @NonNull
    @ColumnInfo(name = "tvShowIsFavorite")
    var isFavorite: Boolean = false
): Parcelable