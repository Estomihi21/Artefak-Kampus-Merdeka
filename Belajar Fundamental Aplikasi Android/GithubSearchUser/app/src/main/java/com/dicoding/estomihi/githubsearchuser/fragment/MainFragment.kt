package com.dicoding.estomihi.githubsearchuser.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.estomihi.githubsearchuser.R
import com.dicoding.estomihi.githubsearchuser.adapter.ListAdapter
import com.dicoding.estomihi.githubsearchuser.model.FollowerModel
import com.dicoding.estomihi.githubsearchuser.model.FollowingModel
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        const val EXTRA_USERNAME = "extra_username"

        fun newInstance(index: Int): MainFragment {
            val fragment = MainFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_SECTION_NUMBER, index)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var adapter: ListAdapter
    private lateinit var followersViewModel: FollowerModel
    private lateinit var followingViewModel: FollowingModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var index = 1
        if (arguments != null) {
            index = arguments?.getInt(ARG_SECTION_NUMBER, 0) as Int
        }

        when (index) {
            1 -> {
                showListFollowers()
                getFollowersViewModel()
            }
            else -> {
                showListFollowers()
                getFollowingViewModel()
            }
        }
    }

    private fun showListFollowers() {
        adapter = ListAdapter()
        recycleView_followers.layoutManager = LinearLayoutManager(context)
        recycleView_followers.adapter = adapter
        recycleView_followers.setHasFixedSize(true)
    }

    private fun getFollowersViewModel() {
        followersViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(FollowerModel::class.java)

        val username = activity?.intent?.getStringExtra(EXTRA_USERNAME)
        showLoading(true)
        followersViewModel.setFollowersUser(username!!)

        followersViewModel.getFollowersUser()
            .observe(viewLifecycleOwner, { followersItem ->
                if (followersItem != null) {
                    adapter.setData(followersItem)
                    showLoading(false)
                }
            }
            )
         }

    private fun getFollowingViewModel() {
        followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(FollowingModel::class.java)

        val username = activity?.intent?.getStringExtra(EXTRA_USERNAME)
        followingViewModel.setFollowingUser(username!!)

        followingViewModel.getFollowingUser()
            .observe(viewLifecycleOwner, { followingItem ->
                if (followingItem != null) {
                    adapter.setData(followingItem)
                    showLoading(false)
                }
            })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }


}