package com.example.gulzaarsahabkishayari

data class ShayariModelClass(var c_id: Int ,var categoryName:String)

data class ShayariDisplay(var s_id :Int ,var shayari:String,var c_id: Int, var fav: Int)


data class FavouriteShayariDisplay(var s_id :Int ,var shayari:String, var fav: Int)
