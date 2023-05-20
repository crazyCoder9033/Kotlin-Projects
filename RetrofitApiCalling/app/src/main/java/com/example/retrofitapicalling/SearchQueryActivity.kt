package com.example.retrofitapicalling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retrofitapicalling.apiClient.*
import com.example.retrofitapicalling.databinding.ActivitySearchQueryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchQueryActivity : AppCompatActivity() {
    lateinit var binding:ActivitySearchQueryBinding
    lateinit var apiInterface: APIInterface
lateinit var keyFound:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySearchQueryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        working()
    }

    private fun working() {
        apiInterface= APIClient.getClient().create(APIInterface::class.java)
        binding.btnSearch.setOnClickListener {

           keyFound= binding.edtSearch.text.toString()


            apiInterface.getSearchedProduct(keyFound).enqueue(object : Callback<Products<ProductsItem>>{
                override fun onResponse(call: Call<Products<ProductsItem>>, response: Response<Products<ProductsItem>>) {
                    var list= response.body()?.products

                    var adapter=SearchProductAdapter(list,this@SearchQueryActivity)

                    var Manager = GridLayoutManager(this@SearchQueryActivity,2)
                    binding.rcvSearchedProducts.layoutManager = Manager
                    binding.rcvSearchedProducts.adapter = adapter

                }
                override fun onFailure(call: Call<Products<ProductsItem>>, t: Throwable) {
                }


            })
        }
    }
}