package com.dicoding.estomihi.thegithubuserapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.estomihi.thegithubuserapp.adapter.ListUserAdapter
import com.dicoding.estomihi.thegithubuserapp.data.UserData
import com.dicoding.estomihi.thegithubuserapp.databinding.ActivityFavoriteUserBinding
import com.dicoding.estomihi.thegithubuserapp.viewmodel.DetailViewModel

class FavoriteUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteUserBinding
    private lateinit var userAdapter: ListUserAdapter
    private lateinit var favoriteViewModel: DetailViewModel

    private fun showLottie(state: Boolean) {
        if (state) {
            binding.apply {
                rvFavorite.visibility = View.GONE
                layoutError.visibility = View.VISIBLE
                lottieNoData.playAnimation()
                lottieNoData.loop(true)
            }
        } else {
            binding.apply {
                rvFavorite.visibility = View.VISIBLE
                binding.layoutError.visibility = View.GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting_menu -> {
                Intent(this, SettingsActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.userFavorite)

        binding.navView.selectedItemId = R.id.navigation_favorite
        binding.navView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    overridePendingTransition(0,0)
                    true
                }
                R.id.navigation_favorite -> {
                    true
                }
                else -> false
            }
        }


        userAdapter = ListUserAdapter()
        userAdapter.notifyDataSetChanged()

        binding.rvFavorite.setHasFixedSize(true)
        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.adapter = userAdapter

        favoriteViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        favoriteViewModel.getFavoriteUser()?.observe(this,{
            if (it != null ){
                val favList = mapListToArrayList(it)
                userAdapter.setData(favList)
                showLottie(false)
                if (it.isEmpty()) {
                    showLottie(true)
                }
            }
        })

    }

    private fun mapListToArrayList(favUser: List<UserData>) : ArrayList<UserData> {
        val listFavUser = ArrayList<UserData>()
        for(user in favUser) {
            val favUserMapped = UserData(
                user.id,
                user.login,
                user.avatar_url
            )
            listFavUser.add(favUserMapped)
        }
        return listFavUser
    }

}