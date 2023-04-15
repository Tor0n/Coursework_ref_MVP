package com.example.myapplication.for_editing

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.myapplication.IntentConstants
import com.example.myapplication.R
import com.example.myapplication.database.MyDbManager
import com.example.myapplication.databinding.EditActivityBinding
import com.example.myapplication.network.DataModel

class EditActivity : AppCompatActivity() {
    lateinit var binding: EditActivityBinding
    private val myDbManager = MyDbManager(this)
    private lateinit var url: String
    //private var timer: CountDownTimer? = null
    private val viewModel: DataModel by viewModels()
    private var employeeCreated = false
    private var tempId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        getIntents()
        viewModel.ifFinished.observe(this@EditActivity) {
            if (it) {
                binding.apply {
                    viewModel.url.observe(this@EditActivity) {
                        url = it
                    }

                    Glide.with(this@EditActivity).load(url).error(R.drawable.ic_error).into(empImage)
                    bAddImage.visibility = View.VISIBLE
                    bEnd.visibility = View.VISIBLE
                    bCancel.visibility = View.VISIBLE
                    curtain.visibility = View.GONE
                }
            }
        }
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
        tempId = i.getIntExtra(IntentConstants.I_ID_KEY, 0)
        Log.d("MyLog", tempId.toString())
        employeeCreated = i.getBooleanExtra(IntentConstants.I_EMP_STATUS_KEY, false)

        binding.editName.setText(i.getStringExtra(IntentConstants.I_NAME_KEY))
        binding.editSalary.setText(i.getStringExtra(IntentConstants.I_SALARY_KEY))

        if (i.getStringExtra(IntentConstants.I_URL_KEY) != null) {
            Glide.with(this@EditActivity)
                .load(i.getStringExtra(IntentConstants.I_URL_KEY))
                .into(binding.empImage)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragHolder, UrlChooseFragment.newInstance())
                .commit()
            viewModel.url.value = i.getStringExtra(IntentConstants.I_URL_KEY)
            binding.bAddImage.visibility = View.GONE
        }
    }

    fun onClickUploadUrl(view: View) {
        binding.apply {
            bAddImage.visibility = View.GONE
            bCancel.visibility = View.GONE
            bEnd.visibility = View.GONE
            curtain.visibility = View.VISIBLE
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragHolder, UrlChooseFragment.newInstance())
                .commit()
        }
    }

    fun onClickFinish(view: View)   {
        val name = binding.editName.text.toString()
        val salary = binding.editSalary.text.toString()
        val id = tempId
        if (name != "") {
            if (salary != "") {
                if (!employeeCreated) {
                    myDbManager.insertInDb(name, salary, url)
                    finish()
                } else {
                    myDbManager.replaceInDb(name, salary, url, id)
                    employeeCreated = false
                    finish()
                    Log.d("MyLog", id.toString())
                }
            } else binding.editName.error = "Введите зарплату сотрудника!"
        } else binding.editName.error = "Введите имя и фамилию сотрудника!"
    }

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