package com.dicoding.estomihi.mymovies.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.estomihi.mymovies.ListAdapter
import com.dicoding.estomihi.mymovies.ListCallback
import com.dicoding.estomihi.mymovies.MainViewModel
import com.dicoding.estomihi.mymovies.databinding.FragmentMovieBinding
import com.dicoding.estomihi.mymovies.detail.DetailActivity
import com.dicoding.estomihi.mymovies.model.MovieEntity
import com.dicoding.estomihi.mymovies.viewmodel.ViewModelFactory


class MovieFragment : Fragment(), ListCallback{

    companion object {
        const val TYPE_MOVIE = "Movie"
    }

    private lateinit var fragmentMovieBinding: FragmentMovieBinding

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
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

            val movieAdapter = ListAdapter(this)

            fragmentMovieBinding.progressBar.visibility = View.VISIBLE
            viewModel.getListNowPlayingMovies().observe(viewLifecycleOwner, Observer { listMovie ->
                fragmentMovieBinding.progressBar.visibility = View.GONE
                movieAdapter.setData(listMovie)
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
        intent.putExtra(DetailActivity.EXTRA_ID, data.id)
        intent.putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
        startActivity(intent)
    }

}