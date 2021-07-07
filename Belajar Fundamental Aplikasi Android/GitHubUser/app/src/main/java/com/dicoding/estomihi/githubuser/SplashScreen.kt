@file:Suppress("DEPRECATION")

package com.dicoding.estomihi.githubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar!!.hide()

        val handler = Handler()
        handler.postDelayed(Runnable {
            val moveintent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(moveintent)
            finish()
        }, 3000)
    }
}