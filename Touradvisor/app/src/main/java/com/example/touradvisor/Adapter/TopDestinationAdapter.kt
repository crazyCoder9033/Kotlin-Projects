package com.example.touradvisor.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.touradvisor.ModelClass.TopDestinationModelClass
import com.example.touradvisor.Fragment.HomeFragment
import com.example.touradvisor.R

class TopDestinationAdapter(
    var topDestination: ArrayList<TopDestinationModelClass>,
    var homeFragment: HomeFragment,var invoke : ((TopDestinationModelClass) -> Unit)
) : RecyclerView.Adapter<TopDestinationAdapter.myAapter>() {
    class myAapter(view : View):RecyclerView.ViewHolder(view) {
        var image : ImageView=view.findViewById(R.id.imgDestination)
        var location : TextView=view.findViewById(R.id.txtLocation)
        var louTopCity : LinearLayout = view.findViewById(R.id.loutTopCity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myAapter {
       var view = LayoutInflater.from(parent.context).inflate(R.layout.top_destination_itemfile,parent,false)
        var v=myAapter(view)
        return v
    }

    override fun getItemCount(): Int {
        return topDestination.size
    }

    override fun onBindViewHolder(holder: myAapter, position: Int) {

        holder.louTopCity.setOnClickListener {
            invoke.invoke(topDestination[position])
        }

        Glide.with(homeFragment).load(topDestination[position].image).placeholder(R.drawable.baseline_image_24).into(holder.image)
        holder.location.setText(topDestination[position].location)
    }
}