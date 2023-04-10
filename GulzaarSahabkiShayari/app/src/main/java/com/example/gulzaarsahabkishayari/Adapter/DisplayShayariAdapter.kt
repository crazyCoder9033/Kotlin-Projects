package com.example.gulzaarsahabkishayari.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.gulzaarsahabkishayari.*

class DisplayShayariAdapter(var un :((ShayariDisplay)-> Unit),var invokeTwo :((Int,Int)-> Unit)
) :RecyclerView.Adapter<DisplayShayariAdapter.MyAdapter>() {
    var shayariText=ArrayList<ShayariDisplay>()
    class MyAdapter(view : View) : RecyclerView.ViewHolder(view) {
    var txtShayari:TextView=view.findViewById(R.id.txtShayari)
        var loutShayari:LinearLayout=view.findViewById(R.id.loutShayari)
        var imgFav:ImageView=view.findViewById(R.id.imgFav)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.shayari_rcv_item,parent,false)
        var v = MyAdapter(view)
        return v
    }
    override fun onBindViewHolder(holder: MyAdapter, position: Int) {

       holder.txtShayari.setText(shayariText[position].shayari)
        Log.e("TAG", "onBindViewHolder: "+shayariText[position].toString() )


        if(shayariText[position].fav == 1)
        {
            holder.imgFav.setImageResource(R.drawable.heartttt)

        }
        else
        {
            holder.imgFav.setImageResource(R.drawable.sss)
        }

        holder.imgFav.setOnClickListener {


           if (shayariText[position].fav == 1)
           {
               invokeTwo.invoke(0,shayariText[position].s_id)

               holder.imgFav.setImageResource(R.drawable.sss)

               shayariText[position].fav=0
               Log.e("texttt", "onBindViewHolder: "+shayariText[position].fav.toString()+"--"+shayariText[position].s_id.toString())
           }
            else{
               invokeTwo.invoke(1,shayariText[position].s_id)

               holder.imgFav.setImageResource(R.drawable.heartttt)
               shayariText[position].fav=1
           }
        }

        holder.loutShayari.setOnClickListener {
            un.invoke(shayariText[position])
        }
    }

    override fun getItemCount(): Int {
       return shayariText.size
    }

    fun updateFunction(shayariText: ArrayList<ShayariDisplay>) {
        this.shayariText=ArrayList()
        this.shayariText.addAll(shayariText)
        Log.e("TAG", "updateFunction: "+shayariText.size )
        notifyDataSetChanged()
    }


}