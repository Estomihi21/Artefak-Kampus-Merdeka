package com.dicoding.estomihi.myfinaljetpack.home.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.TvShowEntity
import com.dicoding.estomihi.myfinaljetpackbinding.FragmentTvShowBinding
import com.dicoding.estomihi.myfinaljetpack.detail.DetailActivity
import com.dicoding.estomihi.myfinaljetpack.home.MainViewModel
import com.dicoding.estomihi.myfinaljetpack.viewmodel.ViewModelFactory
import com.dicoding.estomihi.myfinaljetpack.vo.Status.*
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TvShowFragment : DaggerFragment(), TvShowCallback{

    companion object {
        const val TYPE_TV_SHOW = "TvShow"
    }

    private lateinit var fragmentTvShowBinding : FragmentTvShowBinding
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var factory: ViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
//            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

            val tvShowAdapter = TvShowAdapter(this)

//            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
//            viewModel.getListOnTheAirTvShows().observe(viewLifecycleOwner, Observer { listMovie ->
//                fragmentTvShowBinding.progressBar.visibility = View.GONE
//                tvShowAdapter.setData(listMovie)
//            })

            viewModel.getListOnTheAirTvShows().observe(viewLifecycleOwner, Observer { listTvShow ->
                if (listTvShow != null) {
                    when (listTvShow.status) {
                        LOADING -> {
                            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
                            fragmentTvShowBinding.layoutError.visibility = View.GONE
                        }
                        SUCCESS -> {
                            fragmentTvShowBinding.progressBar.visibility = View.GONE
                            fragmentTvShowBinding.layoutError.visibility = View.GONE
                            fragmentTvShowBinding.rvTvShow.adapter?.let { adapter ->
                                when (adapter) {
                                    is TvShowAdapter -> {
                                        adapter.submitList(listTvShow.data)
                                        adapter.notifyDataSetChanged()
                                    }
                                }
                            }
                        }
                        ERROR -> {
                            fragmentTvShowBinding.progressBar.visibility = View.GONE
                            fragmentTvShowBinding.layoutError.visibility = View.VISIBLE
                            fragmentTvShowBinding.lottieNoData.playAnimation()
                            fragmentTvShowBinding.lottieNoData.loop(true)
                            Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentTvShowBinding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }

    override fun onItemClicked(data: TvShowEntity) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ID, data.tvShowId)
        intent.putExtra(DetailActivity.EXTRA_TYPE, TYPE_TV_SHOW)
        startActivity(intent)
    }


}