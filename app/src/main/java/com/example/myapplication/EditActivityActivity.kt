package com.example.myapplication

import android.content.Intent
import android.graphics.ColorSpace.Model
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.myapplication.database.MyDbManager
import com.example.myapplication.databinding.EditActivityBinding
import com.example.myapplication.network.DataModel
class EditActivityActivity : AppCompatActivity() {
    lateinit var binding: EditActivityBinding
    val imageRequestCode = 10
    var tempImageUri = "empty"
    val myDbManager = MyDbManager(this)
    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == imageRequestCode) {
            binding.empImage.setImageURI(data?.data)
            tempImageUri = data?.data.toString()

        }
        getIntents()
    }

    fun onClickFinish(view: View) {
        val name = binding.editName.text.toString()
        val uri = binding.editURL.text.toString()
        val salary = binding.editSalary.text.toString()

        if(name != "") {
            if (uri != "") {
                if (salary != "") {
                    myDbManager.insertInDb(name, tempImageUri, salary)
                    finish()
                } else binding.editName.error = "Введите зарплату сотрудника!"
            } else binding.editURL.error = "Вставьте ссылку!"
        } else binding.editName.error = "Введите имя и фамилию сотрудника!"
    }

    fun onClickCancel(view: View) {
        finish()
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDB()
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

    fun getIntents() {

        val i = intent

        if (i.getStringExtra(IntentConstants.I_NAME_KEY) != "null") {
            binding.editName.setText(i.getStringExtra(IntentConstants.I_NAME_KEY))
            binding.editSalary.setText(i.getStringExtra(IntentConstants.I_SALARY_KEY))

            if (i.getStringExtra(IntentConstants.I_URL_KEY) != "empty") {
                binding.editURL.setText(i.getStringExtra(IntentConstants.I_URL_KEY))
                binding.empImage.setImageURI(Uri.parse(i.getStringExtra(IntentConstants.I_URL_KEY)))
            }
        }
    }

    fun onClickAddImage(view: View) {
        binding.conChoose.visibility = View.VISIBLE
        startCountDownTimer(5000)
    }
    fun onClickUploadUrl(view: View) {

    }

    fun onClickFinishTypingUrl(view: View) {
        if (binding.editURL.text.isNullOrEmpty()) {
            Toast.makeText(applicationContext, "Upload wasn't successful", Toast.LENGTH_SHORT).show()
            binding.editURL.error = "Вставьте ссылку!"
        } else {

        }
    }
    fun onClickOpenGallery(view: View) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        startActivityForResult(intent, imageRequestCode)
    }

    private fun startCountDownTimer(timeMillis : Long) {
        timer?.cancel()
        timer = object : CountDownTimer(timeMillis, 1000) {
            override fun onTick(timeMillis: Long) {

            }

            override fun onFinish() {
                if (binding.bGetImageFromGallery.visibility == View.VISIBLE) {
                    binding.conChoose.visibility = View.GONE
                    binding.bEnd2.visibility = View.GONE
                }
            }

        }.start()
    }

}