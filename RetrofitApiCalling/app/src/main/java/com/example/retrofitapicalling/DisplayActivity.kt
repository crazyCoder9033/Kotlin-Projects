package com.example.retrofitapicalling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitapicalling.apiClient.APIClient
import com.example.retrofitapicalling.apiClient.APIInterface
import com.example.retrofitapicalling.apiClient.AllContent
import com.example.retrofitapicalling.databinding.ActivityDisplayBinding
import com.example.retrofitapicalling.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DisplayActivity : AppCompatActivity() {
    lateinit var binding: ActivityDisplayBinding
    lateinit var apiInterface: APIInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        working()
        permissions()
    }

    private fun permissions() {

    }

    private fun working() {

        var getId=intent.getIntExtra("id",0)

        apiInterface= APIClient.getClient().create(APIInterface::class.java)

        apiInterface.getAllContent(getId).enqueue(object : Callback<AllContent>{
            override fun onResponse(call: Call<AllContent>, response: Response<AllContent>) {
             var images=  response.body()?.images
                var title=response.body()?.title
                var brand=response.body()?.brand
//                var description=response.body()?.description
//                var category=response.body()?.category
                var discountPercentage=response.body()?.discountPercentage
                var price=response.body()?.price
                var stock=response.body()?.stock
                var rating=response.body()?.rating

                var imageAdapter=ImageAdapter(images)

                binding.VPimg.adapter=imageAdapter
            binding.txtTitle.text=title
            binding.txtBrand.text=brand
            binding.txtDiscountPrice.text=discountPercentage.toString()
            binding.txtPrice.text=price.toString()
            binding.txtStock.text=stock.toString()
                binding.txtRating.text=rating.toString()


            }

            override fun onFailure(call: Call<AllContent>, t: Throwable) {

            }

        })

        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }
}