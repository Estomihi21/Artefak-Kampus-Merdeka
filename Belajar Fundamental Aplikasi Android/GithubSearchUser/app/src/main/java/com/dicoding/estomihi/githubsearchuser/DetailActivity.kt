package com.dicoding.estomihi.githubsearchuser

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.estomihi.githubsearchuser.Data.UserData
import com.dicoding.estomihi.githubsearchuser.adapter.DetailAdapter
import com.dicoding.estomihi.githubsearchuser.adapter.SectionAdapter
import com.dicoding.estomihi.githubsearchuser.database.DatabaseContract
import com.dicoding.estomihi.githubsearchuser.database.DatabaseContract.FavoriteColumns.Companion.CONTENT_URI
import com.dicoding.estomihi.githubsearchuser.helper.MappingHelper
import com.dicoding.estomihi.githubsearchuser.model.DetailModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.tab_layout.*
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailModel
    private lateinit var adapter: DetailAdapter
    private var isFavorite = false
    private var menuItem: Menu? = null
    private lateinit var uriWithId : Uri
    private lateinit var detailUserData : UserData

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        internal val TAG = DetailActivity::class.java.simpleName
    }
    private fun setActionBarTitle(username: String) {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = username
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        adapter = DetailAdapter()
        detailUserData = intent.getParcelableExtra<Parcelable>(EXTRA_USERNAME) as GithubUser

        Picasso.get()
            .load(detailUserData.avatar)
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .error(R.drawable.ic_baseline_account_circle_24)
            .into(image_avatar)
        tv_username.text = detailUserData.username

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)

        detailViewModel.setDetailUserData(detailUserData.username)
        detailViewModel.getDetailUserData().observe(this, Observer { UserData ->
            if(UserData != null) {
                tv_name.text = UserData.name
                tv_repo.text = UserData.repository.toString()
                tv_company.text = UserData.company
                tv_bio.text = UserData.bio
                tv_location.text = UserData.location
            }
        })

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        sectionsPagerAdapter.setData(detailUserData.username)
        view_pager.adapter = sectionsPagerAdapter
        tabs_follow.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f

        val bundle = Bundle()
        val followersFragment = FollowersFragment()
        bundle.putString(FollowersFragment.EXTRA_FOLLOWERS, detailGithubUser.username)
        followersFragment.arguments = bundle

        val followingFragment = FollowingFragment()
        bundle.putString(FollowingFragment.EXTRA_FOLLOWING, detailGithubUser.username)
        followingFragment.arguments = bundle

        setActionBarTitle(detailUserData.username)

        favoriteCheck()

    private fun favoriteCheck() {
        uriWithId = Uri.parse(CONTENT_URI.toString() + "/" + detailUserData.id)
        val cursor = contentResolver.query(uriWithId, null, null, null, null)
        val favorite = MappingHelper.mapCursorToArrayList(cursor)
        for(data in favorite){
            if(detailUserData.id == data.id){
                isFavorite = true
                Log.d(TAG, "This is Favorite User")
            }
        }
    }
    private fun insertFavoriteUser(){
        val values = ContentValues().apply {
            put(DatabaseContract.FavoriteColumns._ID, detailUserData.id)
            put(DatabaseContract.FavoriteColumns.USERNAME, detailUserData.username)
            put(DatabaseContract.FavoriteColumns.AVATAR, detailUserData.avatar)
            put(DatabaseContract.FavoriteColumns.URL, detailUserData.url)
            put(DatabaseContract.FavoriteColumns.DATE, getCurrentDate())
        }
        contentResolver.insert(CONTENT_URI, values)
        val favUser = getString(R.string.favorite_user)
        showSnackMessage("${detailUserData.username} $favUser")
    }

    private fun showSnackMessage(message: String) {
        Snackbar.make(view_pager, message, Snackbar.LENGTH_SHORT).show()

    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)

    }

    private fun deleteFavoriteUser(){
        contentResolver.delete(uriWithId, null, null)
        val unFav = getString(R.string.Not_Fav)
        showSnackMessage("${detailUserData.username} $unFav")
        Log.d(TAG, "Deleted : $uriWithId")
    }

    private fun setFavoriteIcon(){
        if (isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun recyclerViewOptions() {
        recyclerView_detail.layoutManager = LinearLayoutManager(this)
        recyclerView_detail.adapter = adapter
    }

    private fun pagerAdapter() {
        val sectionsPagerAdapter = SectionAdapter(this, supportFragmentManager)
        viewPager.adapter = sectionsPagerAdapter
        tab_layout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menuItem = menu
        setFavoriteIcon()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.changeLanguageSetting) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return when(item.itemId){
            R.id.favorite -> {
                if(isFavorite) deleteFavoriteUser()
                else insertFavoriteUser()
                isFavorite = !isFavorite
                setFavoriteIcon()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


    private fun initToolbar() {
        val actionBar = supportActionBar
        actionBar!!.title = resources.getString(R.string.title_detail)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }


}