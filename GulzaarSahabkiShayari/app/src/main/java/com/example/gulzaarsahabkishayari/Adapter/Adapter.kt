package com.example.gulzaarsahabkishayari.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gulzaarsahabkishayari.R
import com.example.gulzaarsahabkishayari.ShayariModelClass

class Adapter(var shayariList: ArrayList<ShayariModelClass>,var invoke:((ShayariModelClass) -> Unit) ): RecyclerView.Adapter<Adapter.MyAdapter>() {
    class MyAdapter(view : View) : RecyclerView.ViewHolder(view){
        var txtCategoryName:TextView=view.findViewById(R.id.txtCategoryName)
//        var imgHeart:ImageView=view.findViewById(R.id.imgHeart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.category_rcv_item,parent,false)
        var v = MyAdapter(view)
        return v
    }

    override fun onBindViewHolder(holder: MyAdapter, position: Int) {
            holder.txtCategoryName.text=shayariList[position].categoryName

            holder.txtCategoryName.setOnClickListener {
                invoke.invoke(shayariList[position])
            }


    }

    override fun getItemCount(): Int {
        return shayariList.size
    }
}