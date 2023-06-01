package com.example.touradvisor.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.touradvisor.ModelClass.SuratModelClass
import com.example.touradvisor.R

class SuratHotelAdapter(
    var suratDetailsList: ArrayList<SuratModelClass>,
   var  context: Context?,var invoke:((SuratModelClass) -> Unit),

    ) : RecyclerView.Adapter<SuratHotelAdapter.myAdapter>() {
    class myAdapter(view : View) : RecyclerView.ViewHolder(view){
        var details : TextView=view.findViewById(R.id.txtDetails)
        var amount : TextView=view.findViewById(R.id.txtAmount)
        var rating : TextView=view.findViewById(R.id.txtRating)
        var thumbnail : ImageView=view.findViewById(R.id.imgThumbnail)
        var name : TextView=view.findViewById(R.id.txtName)
        var cvHotel: CardView=view.findViewById(R.id.cvHotel)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myAdapter {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.surat_details_item,parent,false)
        var v=myAdapter(view)
        return v
    }

    override fun getItemCount(): Int {
        return suratDetailsList.size
    }

    override fun onBindViewHolder(holder: myAdapter, position: Int) {
        context?.let { Glide.with(it).load(suratDetailsList[position].thumbnail).into(holder.thumbnail) }
        holder.details.setText(suratDetailsList[position].details)
        holder.amount.setText(suratDetailsList[position].amount)
        holder.rating.setText(suratDetailsList[position].rating)
        holder.name.setText(suratDetailsList[position].name)

        holder.cvHotel.setOnClickListener {
            invoke.invoke(suratDetailsList[position])
        }
    }
}