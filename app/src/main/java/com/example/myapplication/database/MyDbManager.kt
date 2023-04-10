package com.example.myapplication.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.myapplication.EmployeeList

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

    fun readDbData(): ArrayList<EmployeeList> {
        val dataList = ArrayList<EmployeeList>()
        val cursor = db?.query(MyDbNameClass.TABLE_NAME, null, null, null, null, null, null)

            while (cursor?.moveToNext()!!) {
                val dataName = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_NAME))
                val dataUrl = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_URI))
                val dataSalary = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_SALARY))
                var item = EmployeeList()
                item.name = dataName
                item.url = dataUrl
                item.salary = dataSalary
                dataList.add(item)
            }
        cursor.close()
        return dataList
    }

    fun closeDb() {
        myDbHelper.close()
    }

}