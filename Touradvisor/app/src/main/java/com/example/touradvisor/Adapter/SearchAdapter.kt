package com.example.touradvisor.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.touradvisor.ModelClass.SuratModelClass
import com.example.touradvisor.R

class SearchAdapter(var searchList: ArrayList<SuratModelClass>) : RecyclerView.Adapter<SearchAdapter.myAdapter>() {
    class myAdapter(view : View):RecyclerView.ViewHolder(view) {

        var rseult : TextView=view.findViewById(R.id.txtGetResult)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myAdapter {
      var view = LayoutInflater.from(parent.context).inflate(R.layout.search_rcv_item,parent,false)
        var v=myAdapter(view)
        return v
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: myAdapter, position: Int) {
        holder.rseult.setText(searchList[position].location)
    }
}