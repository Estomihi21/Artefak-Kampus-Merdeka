package com.dicoding.estomihi.githubsearchuser

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.estomihi.githubsearchuser.Data.UserData
import com.dicoding.estomihi.githubsearchuser.adapter.ListAdapter
import com.dicoding.estomihi.githubsearchuser.model.UserViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ListAdapter
    private lateinit var viewModel: UserViewModel


    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ListAdapter()
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(UserViewModel::class.java)

        viewModel.getUser().observe(this, Observer { userItems ->
            if (userItems != null) {
                adapter.setData(userItems)
                showLoading(false)
            }
        })

        btnSearch.setOnClickListener {
            val username = edtSearch.text.toString()
            if (username.isEmpty()) return@setOnClickListener
            showLoading(true)
            viewModel.setUser(username)
            it.hideKeyboard()
        }

        recyclerViewOptions()
        getDataGithub()
    }

    private fun getDataGithub() {
        val listData = ArrayList<UserData>()

        val url = "https://api.github.com/users"
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token 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")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>, responseBody: ByteArray) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val item = responseObject.getJSONArray("items")

                    for (i in 0 until item.length()) {
                        val user = item.getJSONObject(i)
                        val dataUser = UserData()
                        dataUser.login = user.getString("login")
                        dataUser.avatars = user.getString("avatar_url")
                        listData.add(dataUser)
                    }
                    recyclerViewOptions()
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }

            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>, responseBody: ByteArray, error: Throwable) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Log.d("onFailure", error.message.toString())
            }
        })
    }

    private fun recyclerViewOptions() {
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.changeLanguageSetting) {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun View.hideKeyboard() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }


}