package com.example.myapplication.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel: ViewModel() {

    val mainActivityState: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val request: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}