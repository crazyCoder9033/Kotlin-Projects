package com.example.retrofitapicalling.apiClient

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {

    @GET("/products")
    fun getAllProducts() : Call<Products<Any?>>


    @GET("/products/{id}")
    fun getAllContent(@Path("id") getId : Int) : Call<AllContent>




    @GET("/products/search")
    fun getSearchedProduct(@Query("q") keyFound : String): Call<Products<ProductsItem>>

    @FormUrlEncoded
    @POST("/auth/login")
    fun loginApiCall (@Field("username") username : String,@Field("password") password : String): Call<LoginResponse>


}