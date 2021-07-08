package com.dicoding.picodiploma.tobacom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.tobakomputer.ComputerData

class MainActivity : AppCompatActivity() {
    private lateinit var rvComputer:RecyclerView
    private var list: ArrayList<Computer> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionbar = supportActionBar
        actionbar!!.title= "TobaCom"

        rvComputer = findViewById(R.id.rv_computer)
        rvComputer.setHasFixedSize(true)

        list.addAll(ComputerData.listData)
        showRecyclerList()

    }

    private fun showRecyclerList() {
        rvComputer.layoutManager = LinearLayoutManager(this)
        val computerAdapter = ComputerAdapter(list)
        rvComputer.adapter = computerAdapter
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int){
        when (selectedMode){
           R.id.action_about -> {
               title = "Tentang User"
                val moveIntent = Intent(this@MainActivity,
                        About::class.java)
                startActivity(moveIntent)
            }
        }
    }
}