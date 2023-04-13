package com.example.mycoursework

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication.IntentConstants
import com.example.myapplication.R
import com.example.myapplication.database.MyDbManager
import com.example.myapplication.databinding.EditActivityBinding
import com.example.myapplication.network.DataModel

class EditActivityActivity : AppCompatActivity() {
    lateinit var binding: EditActivityBinding
    private val myDbManager = MyDbManager(this)
    private lateinit var url: String
    //private var timer: CountDownTimer? = null
    private val viewModel: DataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getIntents()
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDB()
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

    private fun getIntents() {

        val i = intent


        binding.editName.setText(i.getStringExtra(IntentConstants.I_NAME_KEY))
        binding.editSalary.setText(i.getStringExtra(IntentConstants.I_SALARY_KEY))

        /*viewModel.changeOrNot.observe(this) {}
    viewModel.changeOrNot.value = true */

        if (i.getStringExtra(IntentConstants.I_URL_KEY) != null)
            Glide.with(this@EditActivityActivity)
                .load(i.getStringExtra(IntentConstants.I_URL_KEY))
                .into(binding.empImage)
        val log = i.getStringExtra(IntentConstants.I_URL_KEY).toString()
        Log.d("MyLog", log)
    }

    fun onClickUploadUrl(view: View) {
        binding.apply {
            bAddImage.visibility = View.GONE
            conChoose.visibility = View.VISIBLE
            bUploadImage.visibility = View.VISIBLE
        }
    }

    fun onClickFinishTypingUrl(view: View) {

        binding.apply {
            url = binding.editURL.text.toString()
            Glide.with(this@EditActivityActivity).load(url).error(R.drawable.ic_error).into(empImage)
            conChoose.visibility = View.GONE
            bUploadImage.visibility = View.GONE
            bAddImage.visibility = View.VISIBLE
        }
    } //В зависимости от значения параметра ViewModel назначить редактирование, ИЗМЕНЯЯ значение в базе данных.

    fun onClickFinish(view: View)   {
        val name = binding.editName.text.toString()
        val salary = binding.editSalary.text.toString()
        url = binding.editURL.text.toString()

        if(name != "") {
            if (salary != "") {
                myDbManager.insertInDb(name, salary, url)
                finish()
            } else binding.editName.error = "Введите зарплату сотрудника!"
        } else binding.editName.error = "Введите имя и фамилию сотрудника!"
    } //В зависимости от значения параметра ViewModel назначить редактирование, ИЗМЕНЯЯ значение в базе данных.

    //Периферия

    fun onClickCancel(view: View) {
        finish()
    }

    /* private fun startCountDownTimer(timeMillis : Long) {
         timer?.cancel()
         timer = object : CountDownTimer(timeMillis, 1000) {
             override fun onTick(timeMillis: Long) {

             }
             override fun onFinish() {
                 if (binding.bGetImageFromGallery.visibility == View.VISIBLE) {
                     binding.conChoose.visibility = View.GONE
                 }
             }

         }.start()
     } */

}