package com.example.myapplication.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel: ViewModel() {
    val url: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val mainActivityState: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val editActivityState: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}