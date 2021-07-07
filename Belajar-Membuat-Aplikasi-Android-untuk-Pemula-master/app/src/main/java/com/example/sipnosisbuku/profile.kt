package com.example.sipnosisbuku

import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class profile :  AppCompatActivity() {
    val imageView = R.drawable.profile
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val actionbar = supportActionBar
        actionbar!!.title = "profile"
        actionbar.setDisplayHomeAsUpEnabled(true)

        val image0: ImageView = findViewById(R.id.img_profil)


        Glide.with(this)
            .load(R.drawable.profile)
            .apply(RequestOptions())
            .into(image0)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}