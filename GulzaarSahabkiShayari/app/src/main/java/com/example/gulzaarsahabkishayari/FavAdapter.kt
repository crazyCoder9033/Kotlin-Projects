package com.example.gulzaarsahabkishayari

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FavAdapter(var FavouriteList: ArrayList<FavouriteShayariDisplay>, var invoke :((Int,Int)-> Unit)) :RecyclerView.Adapter<FavAdapter.MyAdapter>() {
    class MyAdapter(view : View) : RecyclerView.ViewHolder(view) {
        var txtFavShayari:TextView=view.findViewById(R.id.txtFavShayari)
        var imgFav:ImageView=view.findViewById(R.id.imgFav)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.fav_shayari_rcv_item,parent,false)
        var view = MyAdapter(v)
        return view
    }

    override fun onBindViewHolder(holder: MyAdapter, position: Int) {
       holder.txtFavShayari.setText(FavouriteList[position].shayari)


            holder.imgFav.setImageResource(R.drawable.heartttt)


       holder.imgFav.setOnClickListener {

//           if (FavouriteList[position].fav == 1)
//           {

               invoke.invoke(0,FavouriteList[position].s_id)
//               FavouriteList[position].fav=0


//           }
//           else{
//
//               invoke.invoke(1,FavouriteList[position].s_id)
//               FavouriteList[position].fav=1
//
//
//           }

           Log.e("hhhhhhh", "onBindViewHolder: "+FavouriteList[position].fav.toString())

           deleteFav(position)
       }

    }

    override fun getItemCount(): Int {
        return FavouriteList.size
    }

    fun updatedList(FavouriteList: ArrayList<FavouriteShayariDisplay>) {
        this.FavouriteList= FavouriteList
        notifyDataSetChanged()

    }

    fun deleteFav(position: Int)
    {
        FavouriteList.removeAt(position)
        notifyItemRemoved(position)
//        notifyItemRangeRemoved(position,FavouriteList.size)
    }


}