package com.example.myexpensemanager.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myexpensemanager.Adapter.AddCategoryAdapter
import com.example.myexpensemanager.CategoryHelper
import com.example.myexpensemanager.ModelClass.AddModelClass
import com.example.myexpensemanager.databinding.ActivityAddCategoryAcitivityBinding

class AddCategoryAcitivity : AppCompatActivity() {
    lateinit var adapter: AddCategoryAdapter
    lateinit var binding :ActivityAddCategoryAcitivityBinding
    lateinit var dataBase: CategoryHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddCategoryAcitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataBase= CategoryHelper(this)
        working()
    }
    @SuppressLint("SuspiciousIndentation")
    private fun working() {
        binding.btnInsert.setOnClickListener {
            var name = binding.edtAddCategory.text.toString()
            if(name.isEmpty())
            {
                Toast.makeText(this, "Please enter a field", Toast.LENGTH_SHORT).show()
            }
            else if (name.length <=3 || name.length>=15)
            {
                Toast.makeText(this, "please enter a valid field", Toast.LENGTH_SHORT).show()
            }
                     dataBase.insertRecord(name)
            Toast.makeText(this, "Entry Inserted", Toast.LENGTH_SHORT).show()
            val a = Intent(this@AddCategoryAcitivity, HomePageActivity::class.java)
            startActivity(a)
        }
        binding.imgCategoryDone.setOnClickListener {
            val a = Intent(this@AddCategoryAcitivity, HomePageActivity::class.java)
            startActivity(a)
        }
        binding.imgBack.setOnClickListener {
            val a = Intent(this@AddCategoryAcitivity, HomePageActivity::class.java)
            startActivity(a)
        }
    }
}