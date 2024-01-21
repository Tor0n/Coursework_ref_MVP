package com.example.myapplication.AvailableEmployees

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityAvailableEmployeesBinding
import com.example.myapplication.network.ApiService
import com.example.myapplication.network.EmployeeModel
import com.example.myapplication.network.ServiceGenerator
import retrofit2.Call
import retrofit2.Response

class AvailableEmployees : AppCompatActivity(), AvailableEmployeesContract.ViewInterface {
    private lateinit var availableEmployeesPresener: AvailableEmployeesContract.PresentInterface
    lateinit var binding: ActivityAvailableEmployeesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAvailableEmployeesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.title_for_available_employees)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupPresenter()

        //val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        //val call = serviceGenerator.getEmployees()
        val call = availableEmployeesPresener.getCall()
        //запрашивает список и выгружает
        call.enqueue(object: retrofit2.Callback<MutableList<EmployeeModel>> {
            override fun onResponse(call: Call<MutableList<EmployeeModel>>, response: Response<MutableList<EmployeeModel>>) {
                if (response.isSuccessful) {
                    /*binding.rcAvEmployee.apply {
                        layoutManager = GridLayoutManager(this@AvailableEmployees, 2)
                        adapter = AvailableEmployeeAdapter(response.body()!!)
                    }*/
                    displayAvEmployees(response)
                }
            }
            override fun onFailure(call: Call<MutableList<EmployeeModel>>, t: Throwable) {
                //Toast.makeText(applicationContext, "Не удалось загрузить сотрудников...", Toast.LENGTH_LONG).show()
                displayError()
                finish()
            }
        })
    }
    private fun setupPresenter() {
        availableEmployeesPresener = AvailableEmployeesPresenter(this)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) finish()
        return true
    }
    //view interface
    override fun displayAvEmployees (response: Response<MutableList<EmployeeModel>>) {
        binding.rcAvEmployee.apply {
            layoutManager = GridLayoutManager(this@AvailableEmployees, 2)
            adapter = AvailableEmployeeAdapter(response.body()!!)
        }
    }
    override fun displayError() {
        Toast.makeText(applicationContext, "Не удалось загрузить сотрудников...", Toast.LENGTH_LONG).show()
    }
}