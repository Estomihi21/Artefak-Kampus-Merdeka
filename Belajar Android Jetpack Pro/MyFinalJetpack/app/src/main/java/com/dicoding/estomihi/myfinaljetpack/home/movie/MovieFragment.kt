package com.dicoding.estomihi.myfinaljetpack.home.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.MovieEntity
import com.dicoding.estomihi.myfinaljetpackbinding.FragmentMovieBinding
import com.dicoding.estomihi.myfinaljetpack.detail.DetailActivity
import com.dicoding.estomihi.myfinaljetpack.home.MainViewModel
import com.dicoding.estomihi.myfinaljetpack.viewmodel.ViewModelFactory
import com.dicoding.estomihi.myfinaljetpack.vo.Status.*
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieFragment : DaggerFragment(), MovieCallback{

    companion object {
        const val TYPE_MOVIE = "Movie"
    }

    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

            val movieAdapter = MovieAdapter(this)

            viewModel.getListNowPlayingMovies().observe(viewLifecycleOwner, Observer { listMovie ->
                if (listMovie != null) {
                    when (listMovie.status) {
                        LOADING -> {
                            fragmentMovieBinding.progressBar.visibility = View.VISIBLE
                            fragmentMovieBinding.layoutError.visibility = View.GONE
                        }
                        SUCCESS -> {
                            fragmentMovieBinding.progressBar.visibility = View.GONE
                            fragmentMovieBinding.layoutError.visibility = View.GONE
                            fragmentMovieBinding.rvMovie.adapter?.let { adapter ->
                                when (adapter) {
                                    is MovieAdapter -> {
                                        adapter.submitList(listMovie.data)
                                        adapter.notifyDataSetChanged()
                                    }
                                }
                            }
                        }
                        ERROR -> {
                            fragmentMovieBinding.progressBar.visibility = View.GONE
                            fragmentMovieBinding.layoutError.visibility = View.VISIBLE
                            fragmentMovieBinding.lottieNoData.playAnimation()
                            fragmentMovieBinding.lottieNoData.loop(true)
                            Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentMovieBinding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onItemClicked(data: MovieEntity) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ID, data.movieId)
        intent.putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
        startActivity(intent)
    }

}