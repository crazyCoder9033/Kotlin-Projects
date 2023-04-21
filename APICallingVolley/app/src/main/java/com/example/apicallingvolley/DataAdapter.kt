package com.example.apicallingvolley

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(var datalist: ArrayList<ResponseItem>) : RecyclerView.Adapter<DataAdapter.MyAdapter>() {
    class MyAdapter (view : View): RecyclerView.ViewHolder(view){
        var txtID: TextView=view.findViewById(R.id.txtID)
        var txtUserID: TextView=view.findViewById(R.id.txtUserID)
        var txtTitle: TextView=view.findViewById(R.id.txtTitle)
        var txtBody: TextView=view.findViewById(R.id.txtBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter {
       var v= LayoutInflater.from(parent.context).inflate(R.layout.data_rcv_item,parent,false)
        var view = MyAdapter(v)
        return view
    }

    override fun onBindViewHolder(holder: MyAdapter, position: Int) {
        holder.txtID.setText(datalist[position].id.toString())
        holder.txtUserID.setText(datalist[position].userId.toString())
        holder.txtTitle.setText(datalist[position].title)
        holder.txtBody.setText(datalist[position].body)
    }

    override fun getItemCount(): Int {
      return datalist.size
    }
}