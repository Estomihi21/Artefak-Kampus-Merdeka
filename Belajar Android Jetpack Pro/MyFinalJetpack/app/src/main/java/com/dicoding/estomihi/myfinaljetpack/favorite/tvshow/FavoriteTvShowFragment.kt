package com.dicoding.estomihi.myfinaljetpack.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.estomihi.myfinaljetpack.R
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.TvShowEntity
import com.dicoding.estomihi.myfinaljetpackbinding.FragmentFavoriteTvShowBinding
import com.dicoding.estomihi.myfinaljetpack.detail.DetailActivity
import com.dicoding.estomihi.myfinaljetpack.favorite.FavoriteViewModel
import com.dicoding.estomihi.myfinaljetpack.home.tvshow.TvShowAdapter
import com.dicoding.estomihi.myfinaljetpack.home.tvshow.TvShowCallback
import com.dicoding.estomihi.myfinaljetpack.home.tvshow.TvShowFragment.Companion.TYPE_TV_SHOW
import com.dicoding.estomihi.myfinaljetpack.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavoriteTvShowFragment : DaggerFragment(), TvShowCallback {

    private lateinit var fragmentFavoriteTvShowBinding: FragmentFavoriteTvShowBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var favTvShowAdapter: TvShowAdapter

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentFavoriteTvShowBinding = FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(fragmentFavoriteTvShowBinding?.rvFavoriteTvShow)

        if (activity != null) {
            viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            favTvShowAdapter = TvShowAdapter(this)

            viewModel.getListFavoriteTvShow().observe(viewLifecycleOwner, Observer { listFavTvShow ->
                if (listFavTvShow != null) {
                    fragmentFavoriteTvShowBinding.rvFavoriteTvShow.adapter?.let { adapter ->
                        when(adapter) {
                            is TvShowAdapter -> {
                                if (listFavTvShow.isNullOrEmpty()) {
                                    fragmentFavoriteTvShowBinding.rvFavoriteTvShow.visibility = View.GONE
                                    fragmentFavoriteTvShowBinding.tvEmpty.text = resources.getString(R.string.empty_fav_tvShow)
                                    fragmentFavoriteTvShowBinding.layoutError.visibility =
                                        View.VISIBLE
                                    fragmentFavoriteTvShowBinding.lottieNoData.playAnimation()
                                    fragmentFavoriteTvShowBinding.lottieNoData.loop(true)
                                } else {
                                    fragmentFavoriteTvShowBinding.rvFavoriteTvShow.visibility =
                                        View.VISIBLE
                                    fragmentFavoriteTvShowBinding.layoutError.visibility =
                                        View.GONE
                                    adapter.submitList(listFavTvShow)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                }
            })

            with(fragmentFavoriteTvShowBinding.rvFavoriteTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favTvShowAdapter
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
                val tvShowEntity = favTvShowAdapter.getSwipedData(swipedPosition)
                tvShowEntity?.let { viewModel.setFavoriteTvShow(it) }

                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    tvShowEntity?.let { viewModel.setFavoriteTvShow(it) }
                }
                snackbar.show()
            }
        }
    })

    override fun onItemClicked(data: TvShowEntity) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ID, data.tvShowId)
        intent.putExtra(DetailActivity.EXTRA_TYPE, TYPE_TV_SHOW)
        startActivity(intent)
    }

}