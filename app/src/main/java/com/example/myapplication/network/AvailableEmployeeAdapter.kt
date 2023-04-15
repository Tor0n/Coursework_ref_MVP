package com.example.myapplication.network

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.RcAvEmployeeBinding

class AvailableEmployeeAdapter(private val employeeModel: MutableList<EmployeeModel>) : RecyclerView.Adapter<EmployeeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rc_av_employee, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return employeeModel.size
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        return holder.bindView(employeeModel[position])
    }
}

class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = RcAvEmployeeBinding.bind(itemView)

    fun bindView(employeeModel: EmployeeModel) {
        binding.apply {
            empNameRc.text = employeeModel.username
            empEmailRc.text = employeeModel.email
            empPhoneNumberRc.text = employeeModel.phone
        }
    }

}