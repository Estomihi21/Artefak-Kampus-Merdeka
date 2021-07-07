package com.dicoding.estomihi.githubsearchuser.model


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception
import com.dicoding.estomihi.githubsearchuser.Data.UserData

class DetailModel : ViewModel() {

    val dataUser = MutableLiveData<ArrayList<UserData>>()

    fun setUserDetail(username: String) {
        val url = "https://api.github.com/users/$username"
        val listData = ArrayList<UserData>()

        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token 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")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>, responseBody: ByteArray) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val userDetail = UserData()
                    userDetail.apply {
                        login = responseObject.getString("login")
                        name = responseObject.getString("name")
                        location = responseObject.getString("location")
                        company = responseObject.getString("company")
                        repository = responseObject.getInt("public_repos")
                        followers = responseObject.getInt("followers")
                        following = responseObject.getInt("following")
                        avatar = responseObject.getString("avatar_url")
                    }
                    listData.add(userDetail)

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

    fun getDetailUser(): MutableLiveData<ArrayList<UserData>> {
        return dataUser
    }
}
