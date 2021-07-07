package com.example.sipnosisbuku

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class DetailSipnosis : AppCompatActivity() {
    companion object {
        const val EXTRA_JUDUL = "extra_judul"
        const val EXTRA_FOTO = "extra_foto"
        const val EXTRA_PENULIS = "extra_penulis"
        const val EXTRA_HALAMAN = "extra_halaman"
        const val EXTRA_RILIS = "extra_rilis"
        const val EXTRA_SIPNOSIS = "extra_sipnosis"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_sipnosis)
        val actionbar = supportActionBar
        actionbar!!.title = "Detail sipnosis"
        actionbar.setDisplayHomeAsUpEnabled(true)

        val tvjudul: TextView = findViewById(R.id.judulbuku)
        val Ivfoto: ImageView = findViewById(R.id.Ivfoto)
        val tvpenulis: TextView = findViewById(R.id.penulis)
        val tvrilis: TextView = findViewById(R.id.rilis)
        val tvhalaman: TextView = findViewById(R.id.halamanbuku)
        val tvsipnosis: TextView = findViewById(R.id.sipnosis2)

        val Efoto  = intent.getIntExtra(EXTRA_FOTO,0)
        val Ejudul  = intent.getStringExtra(EXTRA_JUDUL)
        val Epenulis  = intent.getStringExtra(EXTRA_PENULIS)
        val Erilis  = intent.getStringExtra(EXTRA_RILIS)
        val Ehalaman  = intent.getStringExtra(EXTRA_HALAMAN)
        val Esipnosis2  = intent.getStringExtra(EXTRA_SIPNOSIS)


        tvjudul.text = Ejudul
                Glide.with(this)
                    .load(Efoto)
                    .apply(RequestOptions())
                    .into(Ivfoto)
        tvpenulis.text = Epenulis
        tvrilis.text = Erilis
        tvhalaman.text = Ehalaman
        tvsipnosis.text = Esipnosis2
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}