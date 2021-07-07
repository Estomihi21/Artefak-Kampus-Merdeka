package com.dicoding.estomihi.githubsearchuser

import android.content.Intent
import android.database.ContentObserver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Parcelable
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.estomihi.githubsearchuser.adapter.FavoriteAdapter
import com.dicoding.estomihi.githubsearchuser.database.DatabaseContract.FavoriteColumns.Companion.CONTENT_URI
import com.dicoding.estomihi.githubsearchuser.helper.MappingHelper
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import com.dicoding.estomihi.githubsearchuser.Data.UserData

class FavoriteActivity : AppCompatActivity() {
    private lateinit var adapter: FavoriteAdapter

    companion object {
        private const val EXTRA_USERNAME = "EXTRA_USERNAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        supportActionBar?.title = getString(R.string.favorite_user)
        adapter = FavoriteAdapter()
        val handlerThread = HandlerThread("Observer")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)
        val myObserver = object : ContentObserver(handler) {
            override fun onChange(self: Boolean) {
                loadFavoritesAsync()
            }
        }
        contentResolver.registerContentObserver(CONTENT_URI, true, myObserver)
        if (savedInstanceState == null) {
            loadFavoritesAsync()
        } else {
            val list = savedInstanceState.getParcelableArrayList<UserData>(EXTRA_USERNAME)
            if (list != null) {
                adapter.listFavorites = list
            }
        }
    }
    private fun loadFavoritesAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            progressBar.visibility = View.VISIBLE
            val defferedFavorites = async(Dispatchers.IO) {
                val cursor = contentResolver?.query(CONTENT_URI, null, null, null, null)
                MappingHelper.mapCursorToArrayList(cursor)
            }
            progressBar.visibility = View.INVISIBLE
            val favorites = defferedFavorites.await()
            if (favorites.size > 0) {
                adapter.listFavorites = favorites
            } else {
                adapter.listFavorites = ArrayList()
                val noFavorites = getString(R.string.Empty)
                showSnackMessages(noFavorites)
            }
        }
    }
    private fun showRecyclerListFavorite() {
        rv_fav_user.layoutManager = LinearLayoutManager(this)
        rv_fav_user.setHasFixedSize(true)
        rv_fav_user.adapter = adapter
        adapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback{
            override fun onItemClicked(data : UserData) {
                val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_USERNAME, data)
                    startActivity(intent)
                }
            })
        }
    private fun showSnackMessages(message: String){
     Snackbar.make(rv_fav_user, message, Snackbar.LENGTH_SHORT)
         .show()
    }

    override fun onResume() {
        super.onResume()
        showRecyclerListFavorite()
        loadFavoritesAsync()
    }
}

