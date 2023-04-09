package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var  binding: ActivityMainBinding
    val dbManager = MyDbManager(this)
    val adapter = RcAdapter(ArrayList())

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

    fun fillAdapter() {
        adapter.updateAdapter(dbManager.readDbData())
    }

}