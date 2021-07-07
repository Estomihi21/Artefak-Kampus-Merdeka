package com.dicoding.estomihi.thegithubuserapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.estomihi.thegithubuserapp.adapter.ListUserAdapter
import com.dicoding.estomihi.thegithubuserapp.databinding.ActivityMainBinding
import com.dicoding.estomihi.thegithubuserapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: ListUserAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navView.selectedItemId = R.id.navigation_home
        binding.navView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_home -> true
                R.id.navigation_favorite -> {
                    startActivity(Intent(this, FavoriteUserActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }
        }
        binding.btnSetting.setOnClickListener {
            Intent(this, SettingsActivity::class.java).also {
                startActivity(it)
            }
        }
        binding.btnSearch.setOnClickListener {
            binding.apply {
                val query = etQuery.text.toString()
                if (query.isEmpty()) {
                    binding.layoutError.visibility = View.VISIBLE
                    binding.lottieNoData.playAnimation()
                    binding.lottieNoData.loop(true)
                    binding.rvUser.visibility = View.GONE
                } else {
                    showLoading(true)
                    mainViewModel.setSearchUser(query)
                }
            }
        }

        userAdapter = ListUserAdapter()
        userAdapter.notifyDataSetChanged()

        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.adapter = userAdapter

        binding.rvUser.setHasFixedSize(true)

        binding.etQuery.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                binding.apply {
                    val query = etQuery.text.toString()
                    if (query.isEmpty()) {
                        binding.layoutError.visibility = View.VISIBLE
                        binding.lottieNoData.playAnimation()
                        binding.lottieNoData.loop(true)
                        binding.rvUser.visibility = View.GONE
                    } else {
                        showLoading(true)
                        mainViewModel.setSearchUser(query)
                    }
                }
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        mainViewModel.getSearchUser().observe(this, {
            if (it != null) {
                userAdapter.setData(it)
                showLoading(false)
                if (it.isEmpty()) {
                    binding.layoutError.visibility = View.VISIBLE
                    binding.lottieNoData.playAnimation()
                    binding.lottieNoData.loop(true)
                }
            }
        })
        binding.lottieNoData.playAnimation()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.rvUser.visibility = View.GONE
            binding.shimmerLayout.startShimmer()
            binding.shimmerLayout.visibility = View.VISIBLE
            binding.layoutError.visibility = View.GONE
        } else {
            binding.rvUser.visibility = View.VISIBLE
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
}