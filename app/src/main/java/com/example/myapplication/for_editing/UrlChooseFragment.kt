package com.example.myapplication.for_editing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.myapplication.databinding.FragmentUrlChooseBinding
import com.example.myapplication.DataModel


class UrlChooseFragment : Fragment() {
    lateinit var binding: FragmentUrlChooseBinding
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUrlChooseBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.con.visibility = View.VISIBLE //view

        dataModel.chooseHide.observe(activity as LifecycleOwner) {
            if(it) binding.con.visibility = View.GONE //view
            else binding.con.visibility = View.VISIBLE
        }

        dataModel.url.observe(activity as LifecycleOwner) {
            binding.editURL.setText(it) //view
        }

        binding.bUploadImage.setOnClickListener {
            dataModel.url.value = binding.editURL.text.toString() //presenter
            dataModel.ifFinished.value = true //presenter
            binding.con.visibility = View.GONE //view
        }

    }
    companion object {
        @JvmStatic
        fun newInstance() = UrlChooseFragment() }
}