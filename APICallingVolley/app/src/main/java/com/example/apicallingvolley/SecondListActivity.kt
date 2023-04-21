package com.example.apicallingvolley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.apicallingvolley.databinding.ActivitySecondListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SecondListActivity : AppCompatActivity() {
    lateinit var binding : ActivitySecondListBinding
    lateinit var mRequestQueue : RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySecondListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        working()
    }

    private fun working() {
        mRequestQueue = Volley.newRequestQueue (this)

        var req =
            JsonArrayRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/comments",null ,
                { response ->

                    val contentlist:ArrayList<SecondItem>
                    val listType: Type?=object : TypeToken<List<SecondItem?>?>() {}.type
                    contentlist= Gson().fromJson(response.toString(),listType)
//                    for (i in 0 until contentlist.size )
//                    {
//                        Log.e("TAG", "working: "+contentlist.get(i).postId )
//                        Log.e("TAG", "working: "+contentlist.get(i).id )
//                        Log.e("TAG", "working: "+contentlist.get(i).name )
//                        Log.e("TAG", "working: "+contentlist.get(i).email )
//                        Log.e("TAG", "working: "+contentlist.get(i).body )
//                    }

                        var adapter=SecondAdapter(contentlist)
                    var Manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    binding.rcvSecondList.layoutManager = Manager
                    binding.rcvSecondList.adapter = adapter


                },
                { error ->

                    Log.e("TAG", "working: "+error.message)
                }
            )
        mRequestQueue.add(req)



    }

    }
