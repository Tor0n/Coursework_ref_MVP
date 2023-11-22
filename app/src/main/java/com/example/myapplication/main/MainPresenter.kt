package com.example.myapplication.main

import android.content.Context
import android.view.View
import com.example.myapplication.database.MyDbManager
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainPresenter (
    private var viewInterface: MainContract.ViewInterface,
    private var dbManager: MyDbManager,
    private var adapter: RcAdapter,
    private var binding: ActivityMainBinding
) : MainContract.PresentInterface {
    private val TAG = "MainPresenter"

    //lateinit var  binding: ActivityMainBinding
    private var job: Job? = null
    override fun stop() {
        dbManager.closeDb()
    }
    override fun getEmployeeList(text: String) {
        dbManager.openDB()
        fillAdapter(text)
    }
    private fun fillAdapter(text: String) {
        job?.cancel()
        job = CoroutineScope(Dispatchers.Main).launch {
            if (dbManager.readDbData("").isEmpty()) binding.ifPresent.visibility = View.VISIBLE
            else binding.ifPresent.visibility = View.GONE
            adapter.updateAdapter(dbManager.readDbData(text))
        }
    }
}