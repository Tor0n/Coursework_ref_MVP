package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityAvailableEmployessBinding
import com.example.myapplication.databinding.RcItemBinding

class AvailableEmployess : AppCompatActivity() {
    lateinit var binding: ActivityAvailableEmployessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAvailableEmployessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}