package com.example.myregistrationfrom.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myregistrationfrom.ModelClass.UserDetailsModelClass
import com.example.myregistrationfrom.R

class DashboardDetailsAdapter(var userDetailList: ArrayList<UserDetailsModelClass>,var invoking: ((UserDetailsModelClass))-> Unit,var deleteInvoke: ((Int)-> Unit),var displayinvoke: ((UserDetailsModelClass)-> Unit)) :RecyclerView.Adapter<DashboardDetailsAdapter.myAdapter>() {

    class myAdapter(view : View) : RecyclerView.ViewHolder(view){
        var imgImageDisplay:ImageView=view.findViewById(R.id.imgImageDisplay)
        var txtNameDisplay:TextView=view.findViewById(R.id.txtNameDisplay)
        var txtAgeDisplay:TextView=view.findViewById(R.id.txtAgeDisplay)
        var txtGenderDisplay:TextView=view.findViewById(R.id.txtGenderDisplay)
        var crdView: CardView=view.findViewById(R.id.crdView)
        var imgEdit: ImageView=view.findViewById(R.id.imgEdit)
        var imgDelete: ImageView=view.findViewById(R.id.imgDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myAdapter {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.rcv_details_display,parent,false)
        var v= myAdapter(view)
        return v
    }

    override fun onBindViewHolder(holder: myAdapter, position: Int) {
        holder.txtNameDisplay.setText(userDetailList[position].fullName)
        holder.txtAgeDisplay.setText(userDetailList[position].age)
        holder.txtGenderDisplay.setText(userDetailList[position].gender)

        holder.imgEdit.setOnClickListener {
            invoking.invoke(userDetailList[position])
        }

        holder.imgDelete.setOnClickListener {
            deleteInvoke.invoke(userDetailList[position].id)
            deleteFav(position)
        }

        holder.imgImageDisplay.setOnClickListener {
            displayinvoke.invoke(userDetailList[position])
        }


        Log.e("TAG", "onBindViewHolder: "+holder.txtAgeDisplay.text+"-"+holder.txtNameDisplay.text )
    }

    override fun getItemCount(): Int {
       return userDetailList.size
    }

    fun updatedData(userDetailList: ArrayList<UserDetailsModelClass>) {
        this.userDetailList= userDetailList
        notifyDataSetChanged()
    }


    fun deleteFav(position: Int)
    {
        userDetailList.removeAt(position)
        notifyItemRemoved(position)
//        notifyItemRangeRemoved(position,FavouriteList.size)
    }
}