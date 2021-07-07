package com.dicoding.estomihi.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.dicoding.estomihi.githubuser.model.User
import de.hdodenhof.circleimageview.CircleImageView

class UserDetail : AppCompatActivity() {
    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val actionBar = supportActionBar
        actionBar?.title = " Details "

        val tvUsername: TextView = findViewById(R.id.tv_username_detail)
        val tvName: TextView = findViewById(R.id.tv_name_detail)
        val tvCompany: TextView = findViewById(R.id.tv_company_detail)
        val tvLocation: TextView = findViewById(R.id.tv_location_detail)
        val tvFollowing: TextView = findViewById(R.id.tv_following_detail)
        val tvFollower: TextView = findViewById(R.id.tv_follower_detail)
        val tvRepo: TextView = findViewById(R.id.tv_repo_detail)
        val civAvatar: CircleImageView = findViewById(R.id.img_avatar_detail)
        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        val username = "@${user.username}"
        val name = user.name
        val company = user.company
        val location = user.location
        val following = user.following
        val follower = user.follower
        val repo = user.repository
        val avatar = user.avatar



        tvUsername.text = username
        tvName.text = name
        tvCompany.text = company
        tvLocation.text = location
        tvFollowing.text = following
        tvFollower.text = follower
        tvRepo.text = repo
        civAvatar.setImageResource(avatar)
    }
}