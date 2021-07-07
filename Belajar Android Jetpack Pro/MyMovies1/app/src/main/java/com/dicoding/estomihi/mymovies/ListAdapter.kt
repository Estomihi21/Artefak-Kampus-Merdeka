package com.dicoding.estomihi.mymovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.estomihi.mymovies.databinding.ItemMoviesBinding
import com.dicoding.estomihi.mymovies.model.MovieEntity

class ListAdapter(private val callback: ListCallback) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private val listData = ArrayList<MovieEntity>()

    fun setData(data : List<MovieEntity>?) {
        if (data == null) return
        this.listData.clear()
        this.listData.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemsListBinding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemsListBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val list = listData[position]
        holder.bind(list)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ListViewHolder(private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listTvMovie: MovieEntity) {
            with(binding) {
                tvMovieTitle.text = listTvMovie.title
                tvGenre.text = listTvMovie.genre
                rbMovie.rating = listTvMovie.rating.toFloat()
                Glide.with(itemView.context)
                        .load(listTvMovie.imgPoster)
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