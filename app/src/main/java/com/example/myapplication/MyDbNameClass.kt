package com.example.myapplication

import android.provider.BaseColumns

object MyDbNameClass: BaseColumns {
    const val TABLE_NAME = "data_base"
    const val COLUMN_NAME_NAME = "employee_name"
    const val COLUMN_NAME_URI = "image_url"
    const val COLUMN_NAME_SALARY = "employee_salary"

    const val DATABASE_VERSION = 2
    const val DATABASE_NAME = "Database.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_NAME_NAME TEXT, $COLUMN_NAME_URI TEXT, $COLUMN_NAME_SALARY TEXT)"
    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

}