package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var adapter:DataAdapter
    lateinit var db1:DataHelper
    lateinit var binding: ActivityMainBinding
    var list=ArrayList<DataModelClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         db1 = DataHelper(this)

         adapter = DataAdapter()
        binding.rcvDataShow.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.rcvDataShow.adapter=adapter

        intview()
    }

    private fun intview() {

        binding.btnSubmit.setOnClickListener {

            var name = binding.edtName.text.toString()
            var mobile=binding.edtMobile.text.toString()
            var gender=binding.edtGender.text.toString()
            var fees=binding.edtFees.text.toString()

            db1.insertRecord(name,mobile,gender,fees)
            list= db1.displayRecord()
            adapter.updateData(list)
        }

        binding.btnDisplay.setOnClickListener {

            Toast.makeText(this, "nope", Toast.LENGTH_SHORT).show()

        }
    }


}