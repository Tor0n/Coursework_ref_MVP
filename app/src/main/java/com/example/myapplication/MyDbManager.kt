package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDbManager(context: Context) {
    val myDbHelper = MyDBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB() {
        db = myDbHelper.writableDatabase
    }

    fun insertInDb(name: String, uri: String, salary: String) {
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_NAME, name)
            put(MyDbNameClass.COLUMN_NAME_URI, uri)
            put(MyDbNameClass.COLUMN_NAME_SALARY, salary)
        }
        db?.insert(MyDbNameClass.TABLE_NAME, null, values)
    }

    fun readDbData(): ArrayList<String> {
        val dataList = ArrayList<String>()
        val cursor = db?.query(MyDbNameClass.TABLE_NAME, null, null, null, null, null, null)

            while (cursor?.moveToNext()!!) {
                val dataText = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_NAME))
                dataList.add(dataText.toString())
            }
        cursor.close()
        return dataList
    }

    fun closeDb() {
        myDbHelper.close()
    }

}