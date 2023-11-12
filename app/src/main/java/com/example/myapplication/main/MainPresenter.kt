package com.example.myapplication.main

import com.example.myapplication.database.MyDbManager

class MainPresenter (
    private var viewInterface: MainContract.ViewInterface,
    private var dbManager: MyDbManager
) : MainContract.PresentInterface {
    private val TAG = "MainPresenter"
}