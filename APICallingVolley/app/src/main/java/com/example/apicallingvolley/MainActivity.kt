package com.example.apicallingvolley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.apicallingvolley.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mRequestQueue : RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        working()
    }

    private fun working() {

        mRequestQueue = Volley.newRequestQueue (this)

        var req =
            JsonArrayRequest(com.android.volley.Request.Method.GET, "https://jsonplaceholder.typicode.com/posts",null ,
                { response ->

                val datalist:ArrayList<ResponseItem>
                val listType:Type?=object : TypeToken<List<ResponseItem?>?>() {}.type
                    datalist=Gson().fromJson(response.toString(),listType)
//                    for (i in 0 until datalist.size )
//                    {
//                        Log.e("TAG", "working: "+datalist.get(i).id )
//                        Log.e("TAG", "working: "+datalist.get(i).userId )
//                        Log.e("TAG", "working: "+datalist.get(i).title )
//                        Log.e("TAG", "working: "+datalist.get(i).body )
//                    }

                    var adapter=DataAdapter(datalist)
                    var Manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    binding.rcvDisplay.layoutManager = Manager
                    binding.rcvDisplay.adapter = adapter


        },
        { error ->

            Log.e("TAG", "working: "+error.message)
        }
        )
            mRequestQueue.add(req)



        }
    }
