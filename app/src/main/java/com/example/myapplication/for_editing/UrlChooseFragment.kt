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

        binding.con.visibility = View.VISIBLE

        dataModel.chooseHide.observe(activity as LifecycleOwner) {
            if(it) binding.con.visibility = View.GONE
            else binding.con.visibility = View.VISIBLE
        }


        dataModel.url.observe(activity as LifecycleOwner) {
            binding.editURL.setText(it)
        }

        binding.bUploadImage.setOnClickListener {
            dataModel.url.value = binding.editURL.text.toString()
            dataModel.ifFinished.value = true
            binding.con.visibility = View.GONE
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = UrlChooseFragment() }

}