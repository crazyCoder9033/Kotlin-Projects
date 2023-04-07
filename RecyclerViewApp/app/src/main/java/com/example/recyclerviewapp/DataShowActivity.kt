package com.example.recyclerviewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recyclerviewapp.databinding.ActivityDataShowBinding

class DataShowActivity : AppCompatActivity() {

   lateinit var binding : ActivityDataShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityDataShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        view()
    }

    private fun view() {

        var name : String? =intent.getStringExtra("name")

        binding.txtName.setText(name)

    }
}