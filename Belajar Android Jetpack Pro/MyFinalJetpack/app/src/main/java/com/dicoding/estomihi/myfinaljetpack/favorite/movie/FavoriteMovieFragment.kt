package com.dicoding.estomihi.myfinaljetpack.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.estomihi.myfinaljetpack.R
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.MovieEntity
import com.dicoding.estomihi.myfinaljetpackbinding.FragmentFavoriteMovieBinding
import com.dicoding.estomihi.myfinaljetpack.detail.DetailActivity
import com.dicoding.estomihi.myfinaljetpack.favorite.FavoriteViewModel
import com.dicoding.estomihi.myfinaljetpack.home.movie.MovieAdapter
import com.dicoding.estomihi.myfinaljetpack.home.movie.MovieCallback
import com.dicoding.estomihi.myfinaljetpack.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavoriteMovieFragment : DaggerFragment(), MovieCallback {

    companion object {
        const val TYPE_MOVIE = "Movie"
    }

    private lateinit var fragmentFavoriteMovieBinding: FragmentFavoriteMovieBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var favMovieAdapter: MovieAdapter


    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentFavoriteMovieBinding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(fragmentFavoriteMovieBinding?.rvFavoriteMovie)

        if (activity != null) {
            viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            favMovieAdapter = MovieAdapter(this)

            viewModel.getListFavoriteMovie().observe(viewLifecycleOwner, Observer { listFavMovie ->
                if (listFavMovie != null) {
                    fragmentFavoriteMovieBinding.rvFavoriteMovie.adapter?.let { adapter ->
                        when(adapter) {
                            is MovieAdapter -> {
                                if (listFavMovie.isNullOrEmpty()) {
                                    fragmentFavoriteMovieBinding.rvFavoriteMovie.visibility = GONE
                                    fragmentFavoriteMovieBinding.tvEmpty.text = resources.getString(R.string.empty_fav_movie)
                                    fragmentFavoriteMovieBinding.layoutError.visibility = VISIBLE
                                    fragmentFavoriteMovieBinding.lottieNoData.playAnimation()
                                    fragmentFavoriteMovieBinding.lottieNoData.loop(true)
                                } else {
                                    fragmentFavoriteMovieBinding.rvFavoriteMovie.visibility = VISIBLE
                                    fragmentFavoriteMovieBinding.layoutError.visibility = GONE
                                    adapter.submitList(listFavMovie)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                }
            })

            with(fragmentFavoriteMovieBinding.rvFavoriteMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favMovieAdapter
            }
        }
    }


    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {

        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = favMovieAdapter.getSwipedData(swipedPosition)
                movieEntity?.let { viewModel.setFavoriteMovie(it) }

                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    movieEntity?.let { viewModel.setFavoriteMovie(it) }
                }
                snackbar.show()
            }
        }
    })

    override fun onItemClicked(data: MovieEntity) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ID, data.movieId)
        intent.putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
        startActivity(intent)
    }

}