package com.example.myexpensemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myexpensemanager.databinding.ActivityAddIncomeBinding
import com.example.myexpensemanager.databinding.ActivityReportsBinding

class ReportsActivity : AppCompatActivity() {
    lateinit var binding: ActivityReportsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=ActivityReportsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}