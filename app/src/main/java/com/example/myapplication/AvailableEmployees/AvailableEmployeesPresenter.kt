package com.example.myapplication.AvailableEmployees

import com.example.myapplication.network.ApiService
import com.example.myapplication.network.EmployeeModel
import com.example.myapplication.network.ServiceGenerator
import retrofit2.Call
import retrofit2.Response

class AvailableEmployeesPresenter (
    private var viewInterface: AvailableEmployeesContract.ViewInterface
): AvailableEmployeesContract.PresentInterface {
    private val TAG = "AvailableEmployeesPresenter"

    override fun getCall(): Call<MutableList<EmployeeModel>> {
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getEmployees()
        return call
    }
}