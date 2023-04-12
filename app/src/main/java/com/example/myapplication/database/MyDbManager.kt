package com.example.myapplication.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.myapplication.EmployeeList

class MyDbManager(context: Context) {
    private val myDbHelper = MyDBHelper(context)
    private var db: SQLiteDatabase? = null

    fun openDB() {
        db = myDbHelper.writableDatabase
    }

    fun insertInDb(name: String, salary: String, uri: String) {
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_NAME, name)
            put(MyDbNameClass.COLUMN_NAME_SALARY, salary)
            put(MyDbNameClass.COLUMN_NAME_URI, uri)
        }
        db?.insert(MyDbNameClass.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun readDbData(): ArrayList<EmployeeList> {
        val dataList = ArrayList<EmployeeList>()
        val cursor = db?.query(MyDbNameClass.TABLE_NAME, null, null, null, null, null, null)

            while (cursor?.moveToNext()!!) {
                val dataName = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_NAME))
                val dataSalary = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_SALARY))
                val dataUri = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_URI))
                val item = EmployeeList()
                item.name = dataName
                item.salary = dataSalary
                item.url = dataUri
                dataList.add(item)
            }
        cursor.close()
        return dataList
    }

    fun closeDb() {
        myDbHelper.close()
    }

}