package com.example.sipnosisbuku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvBuku: RecyclerView
    private var list: ArrayList<Buku> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvBuku = findViewById(R.id.buku)
        rvBuku.setHasFixedSize(true)

        list.addAll(BukuData.listData)
        showRecyclerList()

    }

    private fun showRecyclerList() {
        rvBuku.layoutManager = LinearLayoutManager(this)
        val listBukuAdapter = ListBukuAdapter(list)
        rvBuku.adapter = listBukuAdapter
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }
    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.ic_profile -> {
                val saya = Intent(this@MainActivity, profile::class.java)
                startActivity(saya)
            }
        }
    }

}
