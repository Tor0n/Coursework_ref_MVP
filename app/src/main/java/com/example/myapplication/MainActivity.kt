package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.database.MyDbManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.network.DataModel

class MainActivity : AppCompatActivity() {
    lateinit var  binding: ActivityMainBinding
    val dbManager = MyDbManager(this)
    val adapter = RcAdapter(ArrayList(), this)
    private val dataModel: DataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        init()
    }

    override fun onResume() {
        super.onResume()
        dbManager.openDB()
        if (dbManager.readDbData().isEmpty()) binding.ifPresent.visibility = View.VISIBLE
        fillAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        dbManager.closeDb()
    }

    fun onClickAdd(view: View) {
        val i = Intent(this, EditActivityActivity::class.java)
        startActivity(i)
    }

    private fun init() {
        binding.rcView.layoutManager = GridLayoutManager(this@MainActivity, 3)
        binding.rcView.adapter = adapter
    }

    private fun fillAdapter() {
        adapter.updateAdapter(dbManager.readDbData())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.all_salaries -> {

            }
            R.id.available_employees -> {

            }
        }
        return true
    }

}