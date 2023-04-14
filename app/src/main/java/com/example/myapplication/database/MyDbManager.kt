package com.example.myapplication.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.example.myapplication.EmployeeList

class MyDbManager(context: Context) {
    private val myDbHelper = MyDBHelper(context)
    private var db: SQLiteDatabase? = null

    fun openDB() {
        db = myDbHelper.writableDatabase
    }

    fun insertInDb(name: String, salary: String, url: String) {
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_NAME, name)
            put(MyDbNameClass.COLUMN_NAME_SALARY, salary)
            put(MyDbNameClass.COLUMN_NAME_URL, url)
        }
        db?.insert(MyDbNameClass.TABLE_NAME, null, values)
    }

    fun removeFrDb(id: String) {
        val selectedItemId = BaseColumns._ID + "=$id"
        db?.delete(MyDbNameClass.TABLE_NAME, selectedItemId, null)
    }

    fun replaceInDb(name: String, salary: String, url: String, id: Int) {
        val selectedItemId = BaseColumns._ID + "=$id"
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_NAME, name)
            put(MyDbNameClass.COLUMN_NAME_SALARY, salary)
            put(MyDbNameClass.COLUMN_NAME_URL, url)
        }
        db?.update(MyDbNameClass.TABLE_NAME, values, selectedItemId, null)
    }

    @SuppressLint("Range")
    fun readDbData(searchText: String): ArrayList<EmployeeList> {
        val dataList = ArrayList<EmployeeList>()
        val selection = "${MyDbNameClass.COLUMN_NAME_NAME} like ?"
        val cursor = db?.query(
            MyDbNameClass.TABLE_NAME, null, selection, arrayOf("%$searchText%"), null, null, null
        )

            while (cursor?.moveToNext()!!) {
                val dataName = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_NAME))
                val dataSalary = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_SALARY))
                val dataUrl = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_URL))
                val dataId = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
                val item = EmployeeList()
                item.name = dataName
                item.salary = dataSalary
                item.url = dataUrl
                item.id = dataId
                dataList.add(item)
            }
        cursor.close()
        return dataList
    }

    fun closeDb() {
        myDbHelper.close()
    }

}