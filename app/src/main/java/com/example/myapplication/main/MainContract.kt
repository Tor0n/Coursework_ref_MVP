package com.example.myapplication.main

import com.example.myapplication.EmployeeList

class MainContract {
    interface PresentInterface {
        fun getEmployeeList(text: String)
        fun stop()
    }
    interface ViewInterface {
        fun displayEmployees (employeeList: ArrayList<EmployeeList>)
    }
}