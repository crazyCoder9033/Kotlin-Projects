package com.example.recyclerviewapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  lateinit var   binding : ActivityMainBinding

  var list =ArrayList<DataModelClass>()
  var name= ArrayList<String>()
    var id =ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intview()

    }

    private fun intview() {
        settingdata()
        for (i in 0..name.size-1) {
            var data = DataModelClass(id[i], name[i])
            list.add(data)
        }



        var adapter = DataAdapterClass(list,object:DataInterface{
            @SuppressLint("SuspiciousIndentation")
            override fun onclick(id: Int, name: String) {

                  val  intent=Intent(this@MainActivity,DataShowActivity::class.java)
                    intent.putExtra("id",id)
                intent.putExtra("name",name)
                startActivity(intent)
            }

        })

        var manager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        binding.RcvList.layoutManager=manager
        binding.RcvList.adapter=adapter


    }

    private fun settingdata() {
        name.add("Himanshu")
        name.add("Venc")

        id.add(1001)
        id.add(1002)
    }
}