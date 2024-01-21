package com.example.myapplication.for_editing

import com.example.myapplication.database.MyDbManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditPresenter (
    private var viewInterface: EditContract.ViewInterface,
    private var dbManager: MyDbManager
) : EditContract.PresentInterface {
    private val TAG = "EditPresenter"

    override fun stop() {
        dbManager.closeDb()
    }
    override fun removeFrDb(id: Int) {
        dbManager.openDB()
        dbManager.removeFrDb(id)
    }
    override fun insertInDb(name: String, salary: String, url: String) {
        dbManager.openDB()
        CoroutineScope(Dispatchers.Main).launch {
            dbManager.insertInDb(name, salary, url)
        }
    }

    override fun replaceInDb(name: String, salary: String, url: String, id: Int) {
        dbManager.openDB()
        CoroutineScope(Dispatchers.Main).launch {
            dbManager.replaceInDb(name, salary, url, id)
        }
    }
}