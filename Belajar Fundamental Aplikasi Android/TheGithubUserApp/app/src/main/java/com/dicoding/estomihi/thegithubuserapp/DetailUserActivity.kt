package com.dicoding.estomihi.thegithubuserapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.estomihi.thegithubuserapp.adapter.SectionPagerAdapter
import com.dicoding.estomihi.thegithubuserapp.databinding.ActivityDetailUserBinding
import com.dicoding.estomihi.thegithubuserapp.viewmodel.DetailViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUserActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_LOGIN = "extra_login"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_AVATAR = "extra_avatar"
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.tab_text_1, R.string.tab_text_2,)
        const val TIME_DELAY = 2000
    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var detailViewModel: DetailViewModel
    private var login: String? = null



    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.mainDetail.progressBar.visibility = View.VISIBLE
        } else {
            binding.mainDetail.progressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        login = intent.getStringExtra(EXTRA_LOGIN)
        val avatarUrl = intent.getStringExtra(EXTRA_AVATAR)


        login?.let { setActionBarTitle(it) }

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)


        login?.let {showLoading(true)
            detailViewModel.setDetailUser(it)
        }

        detailViewModel.getDetailUser().observe(this, {
            if (it != null) {
                showLoading(false)
                binding.apply {
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .centerCrop()
                        .into(mainDetail.ivUserDetail)
                    mainDetail.tvNameDetail.text     = it.login
                    mainDetail.tvUsernameDetail.text = it.name
                    mainDetail.tvCompanyDetail.text  = it.company
                    mainDetail.tvLocationDetail.text = it.location
                    val countRepo      = it.public_repos
                    val countFollowers = it.followers
                    val countFollowing = it.following
                    val repo           = resources.getString(R.string.repository, countRepo)
                    val followers      = resources.getString(R.string.followers, countFollowers)
                    val following      = resources.getString(R.string.following, countFollowing)
                    mainDetail.tvRepoDetail.text = repo
                    mainDetail.tvFollower.text   = followers
                    mainDetail.tvFollowing.text  = following

                    val uriGithub = it.html_url
                    val name      = it.name

                    mainDetail.lottieGithub.setOnClickListener {
                        mainDetail.lottieGithub.playAnimation()
                        Toast.makeText(this@DetailUserActivity, "You are currently opening the $name github link", Toast.LENGTH_SHORT).show()
                        Run.after(TIME_DELAY.toLong()) {
                            val uri = Uri.parse(uriGithub)
                            startActivity(Intent(Intent.ACTION_VIEW, uri))
                        }
                    }

                    var isStateFav = false
                    CoroutineScope(Dispatchers.IO).launch {
                        val count = detailViewModel.checkUserFavId(id)
                        withContext(Dispatchers.Main) {
                            if (count != null) {
                                if (count > 0) {
                                    binding.mainDetail.btnFavorite.isChecked = true
                                    isStateFav = true
                                } else {
                                    binding.mainDetail.btnFavorite.isChecked = false
                                    isStateFav = false
                                }
                            }
                        }
                    }

                    mainDetail.btnFavorite.setOnClickListener {
                        isStateFav = !isStateFav
                        if (isStateFav) {
                            detailViewModel.addUserToFav(id, login!!, avatarUrl!!)
                            Toast.makeText(this@DetailUserActivity, "You Add $login To Favorite User", Toast.LENGTH_SHORT).show()
                        } else {
                            detailViewModel.deleteFromFav(id)
                            Toast.makeText(this@DetailUserActivity, "You Remove $login From Favorite User", Toast.LENGTH_SHORT).show()
                        }

                        binding.mainDetail.btnFavorite.isChecked = isStateFav
                    }

                    mainDetail.lottieShare.setOnClickListener {
                        mainDetail.lottieShare.playAnimation()
                        Toast.makeText(this@DetailUserActivity, "You share $name github link", Toast.LENGTH_SHORT).show()
                        Run.after(TIME_DELAY.toLong()) {
                            val intent = Intent(Intent.ACTION_SEND)
                            intent.putExtra(Intent.EXTRA_STREAM, uriGithub)
                            intent.type = "text/plain"
                            startActivity(intent)
                        }
                    }

                }
            }
        })
        val sectionPagerAdapter = SectionPagerAdapter(this, login)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
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

    class Run {
        companion object {
            fun after(delay: Long, process: () -> Unit) {
                Handler(Looper.getMainLooper()).postDelayed({
                    process()
                }, delay)
            }
        }
    }

}
