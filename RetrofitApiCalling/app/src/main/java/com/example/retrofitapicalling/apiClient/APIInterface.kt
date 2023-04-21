package com.example.retrofitapicalling.apiClient

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("/products")
    fun getAllProducts() : Call<Products>


    @GET("/products/1")
    fun getAllContent() : Call<AllContent>

}