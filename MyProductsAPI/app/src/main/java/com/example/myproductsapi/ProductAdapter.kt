package com.example.myproductsapi

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide

class ProductAdapter(var contentlist: ArrayList<ProductsItem>, var mainActivity: MainActivity,var invoke: ((ProductsItem)-> Unit)) :RecyclerView.Adapter<ProductAdapter.myAdapter>() {
    class myAdapter (view : View): RecyclerView.ViewHolder(view){
        var txtBrand : TextView=view.findViewById(R.id.txtBrand)
        var txtTitle : TextView=view.findViewById(R.id.txtTitle)
//        var txtDescription : TextView=view.findViewById(R.id.txtDescription)
        var imgThumbnail : ImageView=view.findViewById(R.id.imgThumbnail)
        var txtPrice : TextView=view.findViewById(R.id.txtPrice)
        var txtDiscountPrice : TextView=view.findViewById(R.id.txtDiscountPrice)
        var txtRating : TextView=view.findViewById(R.id.txtRating)
//        var txtStock : TextView=view.findViewById(R.id.txtStock)
//        var txtCategory : TextView=view.findViewById(R.id.txtCategory)
//        var VPimg : ViewPager=view.findViewById(R.id.VPimg)
        var loutClick:LinearLayout=view.findViewById(R.id.loutClick)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myAdapter {

        var v = LayoutInflater.from(parent.context).inflate(R.layout.rcv_product_item,parent,false)
        var view = myAdapter(v)
        return view
    }

    override fun onBindViewHolder(holder: myAdapter, position: Int) {
       holder.txtBrand.setText(contentlist[position].brand)
       holder.txtTitle.setText(contentlist[position].title)
//       holder.txtDescription.setText(contentlist[position].description)
       holder.txtPrice.setText(contentlist[position].price.toString())
       holder.txtDiscountPrice.setText(contentlist[position].discountPercentage.toString())
       holder.txtRating.setText(contentlist[position].rating.toString())
//       holder.txtStock.setText(contentlist[position].stock.toString())
//       holder.txtCategory.setText(contentlist[position].category)



//        var imageAdapter=ImageAdapter(contentlist[position].images,contentlist)
//        holder.VPimg.adapter=imageAdapter

        Glide.with(mainActivity ).load("https://i.dummyjson.com/data/products/${contentlist[position].id}/thumbnail.jpg").into(holder.imgThumbnail)

        holder.loutClick.setOnClickListener {
            invoke.invoke(contentlist[position])
        }


    }

    override fun getItemCount(): Int {
       return contentlist.size
    }


}