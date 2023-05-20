package com.example.retrofitapicalling

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitapicalling.apiClient.ProductsItem
import com.example.retrofitapicalling.login.MainActivity

class ProductsAdapter(var list: List<ProductsItem?>?,
                      var mainActivity: MainActivity,
                      var invoke:((ProductsItem?)-> Unit)) : RecyclerView.Adapter<ProductsAdapter.myAdapter>() {
    class myAdapter(view : View): RecyclerView.ViewHolder(view){
        var txtBrand : TextView =view.findViewById(R.id.txtBrand)
        var txtTitle : TextView =view.findViewById(R.id.txtTitle)
        var imgThumbnail : ImageView =view.findViewById(R.id.imgThumbnail)
        var txtPrice : TextView=view.findViewById(R.id.txtPrice)
        var txtDiscountPrice : TextView=view.findViewById(R.id.txtDiscountPrice)
        var txtRating : TextView=view.findViewById(R.id.txtRating)
        var loutClick: LinearLayout =view.findViewById(R.id.loutClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myAdapter {
       var v = LayoutInflater.from(parent.context).inflate(R.layout.rcv_product_item,parent,false)
        var view = myAdapter(v)
        return  view
    }

    override fun onBindViewHolder(holder: myAdapter, position: Int) {
        holder.txtBrand.setText(list!![position]?.brand)
        holder.txtTitle.setText(list!![position]?.title)

        holder.txtPrice.setText(list!![position]?.price.toString())
        holder.txtDiscountPrice.setText(list!![position]?.discountPercentage.toString())
        holder.txtRating.setText(list!![position]?.rating.toString())

        Glide.with(mainActivity ).load("https://i.dummyjson.com/data/products/${list!![position]?.id}/thumbnail.jpg").into(holder.imgThumbnail)
        holder.loutClick.setOnClickListener {
            invoke.invoke(list!![position])
        }
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

}

//
//