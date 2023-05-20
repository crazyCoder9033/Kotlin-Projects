package com.example.videoplayer

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView

class Adapter(var videoLinkList: ArrayList<String>) :RecyclerView.Adapter<Adapter.myAdapter>() {
    class myAdapter(view :View) : RecyclerView.ViewHolder(view){

        var vp : VideoView=view.findViewById(R.id.vp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myAdapter {
       var v=LayoutInflater.from(parent.context).inflate(R.layout.item_rcv,parent,false)
        var vv =myAdapter(v)
        return vv
    }

    override fun onBindViewHolder(holder: myAdapter, position: Int) {
        
    }

    override fun getItemCount(): Int {
       return videoLinkList.size
    }
}