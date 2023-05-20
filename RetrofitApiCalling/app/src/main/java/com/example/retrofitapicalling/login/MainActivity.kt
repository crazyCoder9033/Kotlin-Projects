package com.example.retrofitapicalling.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retrofitapicalling.DisplayActivity
import com.example.retrofitapicalling.ProductsAdapter
import com.example.retrofitapicalling.apiClient.APIClient
import com.example.retrofitapicalling.apiClient.APIInterface
import com.example.retrofitapicalling.apiClient.Products
import com.example.retrofitapicalling.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var apiInterface: APIInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        working()
    }

    private fun working() {

        apiInterface=APIClient.getClient().create(APIInterface::class.java)

        apiInterface.getAllProducts().enqueue(object : Callback<Products<Any?>> {
            override fun onResponse(call: Call<Products<Any?>>, response: Response<Products<Any?>>) {

               var list= response.body()?.products

//                for (i in 0 until list?.size!!)
//                {
//                    Log.e("TAG", "onResponse: "+list[i]?.title )
//                }

                var adapter= ProductsAdapter(list,this@MainActivity){

                    var intent = Intent(this@MainActivity, DisplayActivity::class.java)

                    intent.putExtra("id",it?.id)


                    startActivity(intent)
                }

                var Manager = GridLayoutManager(this@MainActivity,2)
                binding.rcvProductsList.layoutManager = Manager
                binding.rcvProductsList.adapter = adapter

            }

            override fun onFailure(call: Call<Products<Any?>>, t: Throwable) {

            }


        })
    }
}