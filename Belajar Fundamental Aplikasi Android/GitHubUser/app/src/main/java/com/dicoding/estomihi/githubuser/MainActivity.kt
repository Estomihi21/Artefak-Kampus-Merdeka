package com.dicoding.estomihi.githubuser

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.estomihi.githubuser.model.User

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: UserAdapter
    private lateinit var dataAvatar: TypedArray
    private lateinit var dataName: Array<String>
    private lateinit var dataUsername: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataFollower: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataLocation: Array<String>
    private var users = arrayListOf<User>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView: ListView = findViewById(R.id.lv_list)
        adapter = UserAdapter(this)
        listView.adapter = adapter

        prepare()
        addItem()

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val user = users[position]
            val userDetailIntent = Intent(this@MainActivity, UserDetail::class.java)
            userDetailIntent.putExtra(UserDetail.EXTRA_USER, user)
            startActivity(userDetailIntent)
        }
    }

    private fun addItem() {
        for (position in dataName.indices) {
            val user = User(
                dataAvatar.getResourceId(position, -1),
                dataUsername[position],
                dataName[position],
                dataCompany[position],
                dataLocation[position],
                dataRepository[position],
                dataFollower[position],
                dataFollowing[position]
            )
            users.add(user)
        }
        adapter.users = users
    }

    private fun prepare() {
        dataName = resources.getStringArray(R.array.data_name)
        print("love")
        dataCompany = resources.getStringArray(R.array.data_company)
        dataLocation = resources.getStringArray(R.array.data_location)
        dataAvatar = resources.obtainTypedArray(R.array.data_avatar)
        dataUsername = resources.getStringArray(R.array.data_username)
        dataRepository = resources.getStringArray(R.array.data_repository)
        dataFollower = resources.getStringArray(R.array.data_followers)
        dataFollowing = resources.getStringArray(R.array.data_following)
    }
}