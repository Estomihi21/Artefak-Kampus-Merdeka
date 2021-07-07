package com.dicoding.estomihi.githubsearchuser.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.estomihi.githubsearchuser.Data.UserData
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import java.lang.Exception


class FollowerModel : ViewModel() {

    val dataUser = MutableLiveData<ArrayList<UserData>>()

    fun setFollowersUser(username: String) {
        val listData = ArrayList<UserData>()

        val url = "https://api.github.com/users/$username/followers"

        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token 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")
        client.addHeader("User-agent", "request")
        client.get(url, object: AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?,
                                   responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responseArray = JSONArray(result)
                    for (i in 0 until responseArray.length()) {
                        val followers = responseArray.getJSONObject(i)
                        val dataUser = UserData()
                        dataUser.login = followers.getString("login")
                        dataUser.avatar = followers.getString("avatar_url")
                        listData.add(dataUser)
                    }
                    dataUser.postValue(listData)

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

    fun getFollowersUser(): LiveData<ArrayList<UserData>> {
        return dataUser
    }
}
