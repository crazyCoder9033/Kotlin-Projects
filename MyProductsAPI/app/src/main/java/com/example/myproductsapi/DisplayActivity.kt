package com.example.myproductsapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myproductsapi.databinding.ActivityDisplayBinding
import com.google.gson.Gson

class DisplayActivity : AppCompatActivity() {
    lateinit var binding: ActivityDisplayBinding
    lateinit var mRequest : RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        working()
    }

    private fun working() {





        var id = intent.getIntExtra("id",0)
        mRequest = Volley.newRequestQueue (this)

        var req = JsonObjectRequest(Request.Method.GET, "https://dummyjson.com/products/$id",null ,
            { response ->
                var imagelist = Gson().fromJson(response.toString(), ProductsItem::class.java)
                var imageAdapter=ImageAdapter(imagelist.images)

                binding.VPimg.adapter=imageAdapter
                binding.txtTitle.setText(imagelist.title)
                binding.txtBrand.setText(imagelist.brand)
                binding.txtPrice.text=imagelist.price.toString()
                binding.txtDiscountPrice.text=imagelist.discountPercentage.toString()
                binding.txtRating.text=imagelist.rating.toString()

                binding.txtStock.text=imagelist.stock.toString()


            },
            { error->

            }
        )
        mRequest.add(req)


        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }
}