package com.dicoding.estomihi.thegithubuserapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.estomihi.thegithubuserapp.adapter.ListUserAdapter
import com.dicoding.estomihi.thegithubuserapp.databinding.FragmentMainBinding
import com.dicoding.estomihi.thegithubuserapp.viewmodel.DetailViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var followAdapter: ListUserAdapter
    private lateinit var detailViewModel: DetailViewModel

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_USERNAME = "arg_username"

        @JvmStatic
        fun newInstance(index: Int, username: String?) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                    putString(ARG_USERNAME, username)
                }
            }
    }

    private var username: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            username = arguments?.getString(ARG_USERNAME)

            setupRecyclerViewFollow()

            setupViewPagerFollow()
        }
    }

    private fun setupRecyclerViewFollow() {
        binding.rvFollow.apply {
            followAdapter = ListUserAdapter()
            layoutManager = LinearLayoutManager(activity)
            adapter = followAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupViewPagerFollow() {
        var index = arguments?.getInt(ARG_SECTION_NUMBER,0)
        when(index) {
            1 -> showObserverFollowers()
            2 -> showObserverFollowing()
        }
    }

    private fun showObserverFollowers() {
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        username?.let { showLoading(true)
            detailViewModel.setFollowersUser(it) }
        detailViewModel.getFollowersUser().observe(viewLifecycleOwner, Observer{
            if (it != null) {
                showLoading(false)
                followAdapter.setData(it)
                if (it.isEmpty()) {
                    binding.layoutError.visibility = View.VISIBLE
                    binding.lottieNoData.playAnimation()
                    binding.lottieNoData.loop(true)
                }
            }
        })
    }

    private fun showObserverFollowing() {
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        username?.let { showLoading(false)
            detailViewModel.setFollowingUser(it) }
        detailViewModel.getFollowingUser().observe(viewLifecycleOwner, Observer{
            if (it != null) {
                showLoading(false)
                followAdapter.setData(it)
                if (it.isEmpty()) {
                    binding.layoutError.visibility = View.VISIBLE
                    binding.lottieNoData.playAnimation()
                    binding.lottieNoData.loop(true)
                }
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.rvFollow.visibility = View.GONE
            binding.shimmerLayout.startShimmer()
            binding.shimmerLayout.visibility = View.VISIBLE
            binding.layoutError.visibility = View.GONE
        } else {
            binding.rvFollow.visibility = View.VISIBLE
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.visibility = View.GONE
            binding.layoutError.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerLayout.startShimmer()
    }

    override fun onPause() {
        binding.shimmerLayout.stopShimmer()
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvFollow.adapter = null
        _binding = null
    }

}