package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.databinding.EditActivityBinding

class EditActivityActivity : AppCompatActivity() {
    lateinit var binding: EditActivityBinding
    val myDbManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onClickFinish(view: View) {

        val name = binding.editName.text.toString()
        val url = binding.editURL.text.toString()
        val salary = binding.editSalary.text.toString()

        if(name != "") {
            if (url != "") {
                if (salary != "") {
                    salary.toInt()

                } else binding.editName.error = "Введите зарплату сотрудника!"
            } else binding.editURL.error = "Вставьте ссылку!"
        } else binding.editName.error = "Введите имя и фамилию сотрудника!"

        finish()
    }

    fun onClickUploadImage(view: View) {
        if (binding.editURL.text.isNullOrEmpty()) {
            Toast.makeText(applicationContext, "Upload wasn't successful", Toast.LENGTH_SHORT).show()
            binding.editURL.error = "Вставьте ссылку!"
        } else {

        }
    }
}