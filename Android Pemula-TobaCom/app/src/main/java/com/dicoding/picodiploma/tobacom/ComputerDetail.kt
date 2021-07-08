package com.dicoding.picodiploma.tobacom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ComputerDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_computer_detail)
        val actionbar = supportActionBar
        "Spesifikasi Komputer".also { actionbar!!.title = it }
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val tvSetName: TextView = findViewById(R.id.tv_set_name)
        val imgSetPhoto: ImageView = findViewById(R.id.img_item_photo)
        val tvSetIden: TextView = findViewById(R.id.tv_item_details)

        val tName = intent.getStringExtra(EXTRA_NAME)
        val tImg = intent.getIntExtra(EXTRA_PHOTO, 0)
        val tIDetail= intent.getStringExtra(EXTRA_DETAIL)

        tvSetName.text = tName
        Glide.with(this)
                .load(tImg)
                .apply(RequestOptions())
                .into(imgSetPhoto)
        tvSetIden.text = tIDetail

    }
    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_DETAIL = "extra_detail"
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}