package com.dicoding.estomihi.myfinaljetpack.home.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.estomihi.myfinaljetpack.R
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.MovieEntity
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.TvShowEntity
import com.dicoding.estomihi.myfinaljetpackbinding.ItemMovieBinding
import com.dicoding.estomihi.myfinaljetpack.home.movie.MovieAdapter.Companion.API_IMAGE_ENDPOINT
import com.dicoding.estomihi.myfinaljetpack.home.movie.MovieAdapter.Companion.ENDPOINT_POSTER_SIZE_W185

class TvShowAdapter (private val callback: TvShowCallback) : PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {

            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemsListBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsListBinding)
    }

    fun getSwipedData(swipedPosition: Int): TvShowEntity? = getItem(swipedPosition)

    inner class TvShowViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listTvMovie: TvShowEntity) {
            with(binding) {
                tvMovieTitle.text = listTvMovie.title
                tvVote.text = itemView.context.resources.getString(R.string.vote_average, listTvMovie.voteAverage)
                Glide.with(itemView.context)
                    .load(API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W185 + listTvMovie.posterPath)
                    .centerCrop()
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(ivPoster)

                itemView.setOnClickListener {
                    callback.onItemClicked(listTvMovie)
                }

            }
        }
    }
}