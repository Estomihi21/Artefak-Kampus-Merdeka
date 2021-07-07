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
import com.dicoding.estomihi.mymovies.databinding.FragmentTVShowBinding
import com.dicoding.estomihi.mymovies.detail.DetailActivity
import com.dicoding.estomihi.mymovies.model.MovieEntity
import com.dicoding.estomihi.mymovies.viewmodel.ViewModelFactory


class TVShowFragment : Fragment(), ListCallback{

    companion object {
        const val TYPE_TV_SHOW = "TvShow"
    }

    private lateinit var fragmentTvShowBinding : FragmentTVShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        fragmentTvShowBinding = FragmentTVShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

            val tvShowAdapter = ListAdapter(this)

            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
            viewModel.getListOnTheAirTvShows().observe(viewLifecycleOwner, Observer { listMovie ->
                fragmentTvShowBinding.progressBar.visibility = View.GONE
                tvShowAdapter.setData(listMovie)
            })

            with(fragmentTvShowBinding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }

    override fun onItemClicked(data: MovieEntity) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ID, data.id)
        intent.putExtra(DetailActivity.EXTRA_TYPE, TYPE_TV_SHOW)
        startActivity(intent)
    }

}