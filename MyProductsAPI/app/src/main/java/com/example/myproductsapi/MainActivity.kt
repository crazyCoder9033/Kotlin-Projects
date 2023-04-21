package com.example.myproductsapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myproductsapi.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var mRequest : RequestQueue
    var isProgressVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        working()
    }

    private fun working() {

        mRequest = Volley.newRequestQueue (this)

        var req = JsonObjectRequest(Request.Method.GET, "https://dummyjson.com/products",null ,
                { response ->
                    var  contentlist= Gson().fromJson(response.toString(),Product::class.java)
                    var adapter=ProductAdapter(contentlist.products,this){


//                        if (isProgressVisible)
//                        {
//                            binding.pb.visibility= View.GONE
//                            isProgressVisible = false
//                        }
//                        else
//                        {
//                            isProgressVisible = true
//                            binding.pb.visibility= View.VISIBLE
//                        }



                        var intent = Intent(this,DisplayActivity::class.java)

                        intent.putExtra("id",it.id)

                        startActivity(intent)
                    }
                    var Manager = GridLayoutManager(this,2)
                    binding.rcvProductsList.layoutManager = Manager
                    binding.rcvProductsList.adapter = adapter
                },
                { error ->
                    Log.e("TAG", "working: "+error.message)
                }
            )

        mRequest.add(req)





    }
}
