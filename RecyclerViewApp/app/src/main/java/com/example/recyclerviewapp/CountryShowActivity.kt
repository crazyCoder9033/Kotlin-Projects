package com.example.recyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recyclerviewapp.databinding.ActivityCountryCodeBinding
import com.example.recyclerviewapp.databinding.ActivityCountryShowBinding
import com.example.recyclerviewapp.databinding.ActivityDataShowBinding

class CountryShowActivity : AppCompatActivity() {

    lateinit var binding: ActivityCountryShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCountryShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var name : String?=intent.getStringExtra("name")
        binding.txtName.setText(name)
    }



}