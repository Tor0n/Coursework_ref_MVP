package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RcItemBinding

class RcAdapter(arrayList1: ArrayList<String>): RecyclerView.Adapter<RcAdapter.ViewHolder>() {
    var nameList = arrayList1

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RcItemBinding.bind(itemView)

        fun setData(name: String) {
            binding.empNameRc.text = name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.rc_item, parent, false))
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(nameList[position])
    }

    fun updateAdapter(itemList: ArrayList<String>) {
        nameList.clear()
        nameList.addAll(itemList)
        notifyDataSetChanged()
    }

}