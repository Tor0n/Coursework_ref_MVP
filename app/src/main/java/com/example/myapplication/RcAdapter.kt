package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RcItemBinding
class RcAdapter(arrayList1: ArrayList<EmployeeList>, contextM: Context): RecyclerView.Adapter<RcAdapter.ViewHolder>() {
    var nameList = arrayList1
    var context = contextM

    class ViewHolder(itemView: View, contextV: Context) : RecyclerView.ViewHolder(itemView) {
        private val binding = RcItemBinding.bind(itemView)
        val context = contextV

        fun setData(employee: EmployeeList) {
            binding.empNameRc.text = employee.name
            itemView.setOnClickListener {
                val intent = Intent(context, EditActivityActivity:: class.java).apply {
                    putExtra(IntentConstants.I_NAME_KEY, employee.name)
                    putExtra(IntentConstants.I_URL_KEY, employee.url)
                    putExtra(IntentConstants.I_SALARY_KEY, employee.salary)
                }
                context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.rc_item, parent, false), context)
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(nameList[position])
    }

    fun updateAdapter(itemList: ArrayList<EmployeeList>) {
        nameList.clear()
        nameList.addAll(itemList)
        notifyDataSetChanged()
    }

}