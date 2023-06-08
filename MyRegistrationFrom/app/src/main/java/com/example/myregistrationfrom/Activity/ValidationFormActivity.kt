package com.example.myregistrationfrom.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myregistrationfrom.R
import com.example.myregistrationfrom.databinding.ActivityValidationFormBinding

class ValidationFormActivity : AppCompatActivity() {
    lateinit var binding: ActivityValidationFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityValidationFormBinding.inflate(layoutInflater)

        setContentView(binding.root)

        workingClass()
    }

    private fun workingClass() {


    }
}