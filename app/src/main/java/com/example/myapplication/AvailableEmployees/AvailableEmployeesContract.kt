package com.example.myapplication.AvailableEmployees

import com.example.myapplication.network.EmployeeModel
import retrofit2.Call
import retrofit2.Response

class AvailableEmployeesContract {
    interface PresentInterface {
        fun getCall(): Call<MutableList<EmployeeModel>>
    }
    interface ViewInterface {
        fun displayAvEmployees (response: Response<MutableList<EmployeeModel>>)
        fun displayError()
    }
}