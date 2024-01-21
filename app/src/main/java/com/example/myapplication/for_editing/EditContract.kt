package com.example.myapplication.for_editing

class EditContract {
    interface PresentInterface {
        fun removeFrDb(id: Int)
        fun insertInDb(name: String, salary: String, url: String)
        fun replaceInDb(name: String, salary: String, url: String, id: Int)
        fun stop()
    }
    interface ViewInterface {
        fun display()
        fun undisplay()
    }
}