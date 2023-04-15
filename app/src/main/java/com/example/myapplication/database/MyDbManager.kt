package com.example.myapplication.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.example.myapplication.EmployeeList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class MyDbManager(context: Context) {
    private val myDbHelper = MyDBHelper(context)
    private var db: SQLiteDatabase? = null

    fun openDB() {
        db = myDbHelper.writableDatabase
    }

    suspend fun insertInDb(name: String, salary: String, url: String) = withContext(Dispatchers.IO) {
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_NAME, name)
            put(MyDbNameClass.COLUMN_NAME_SALARY, salary)
            put(MyDbNameClass.COLUMN_NAME_URL, url)
        }
        db?.insert(MyDbNameClass.TABLE_NAME, null, values)
    }

    fun removeFrDb(id: Int) {
        val selectedItemId = BaseColumns._ID + "=$id"
        db?.delete(MyDbNameClass.TABLE_NAME, selectedItemId, null)
    }

    suspend fun replaceInDb(name: String, salary: String, url: String, id: Int)  = withContext(Dispatchers.IO){
        val selectedItemId = BaseColumns._ID + "=$id"
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_NAME, name)
            put(MyDbNameClass.COLUMN_NAME_SALARY, salary)
            put(MyDbNameClass.COLUMN_NAME_URL, url)
        }
        db?.update(MyDbNameClass.TABLE_NAME, values, selectedItemId, null)
    }

    @SuppressLint("Range")
    suspend fun readDbData(searchText: String): ArrayList<EmployeeList> = withContext(Dispatchers.IO) {
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
        return@withContext dataList
    }

    fun closeDb() {
        myDbHelper.close()
    }

}