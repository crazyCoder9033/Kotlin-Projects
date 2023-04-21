package com.example.apicallingvolley

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SecondAdapter(var contentlist: ArrayList<SecondItem>) :RecyclerView.Adapter<SecondAdapter.myAdapter>() {
    class myAdapter(view : View) : RecyclerView.ViewHolder(view) {
        var txtPostID:TextView=view.findViewById(R.id.txtPostID)
        var txtID: TextView =view.findViewById(R.id.txtID)
        var txtUserID: TextView =view.findViewById(R.id.txtUserID)
        var txtTitle: TextView =view.findViewById(R.id.txtTitle)
        var txtBody: TextView =view.findViewById(R.id.txtBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myAdapter {
        var v= LayoutInflater.from(parent.context).inflate(R.layout.data_second_item,parent,false)
        var view = myAdapter(v)
        return view
    }

    override fun onBindViewHolder(holder: myAdapter, position: Int) {
        holder.txtPostID.setText(contentlist[position].postId.toString())
        holder.txtID.setText(contentlist[position].id.toString())
        holder.txtUserID.setText(contentlist[position].email)
        holder.txtTitle.setText(contentlist[position].name)
        holder.txtBody.setText(contentlist[position].body)
    }

    override fun getItemCount(): Int {
        return contentlist.size
    }
}