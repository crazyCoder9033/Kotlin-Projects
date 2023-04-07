package com.example.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter() : RecyclerView.Adapter<DataAdapter.MyAdapter>() {
    var list=ArrayList<DataModelClass>()
    class MyAdapter(v :View): RecyclerView.ViewHolder(v) {

        var id : TextView=v.findViewById(R.id.txtId)
        var name: TextView=v.findViewById(R.id.txtName)
        var mobile: TextView=v.findViewById(R.id.txtMobile)
        var gender : TextView=v.findViewById(R.id.txtGender)
        var fees: TextView=v.findViewById(R.id.txtFees)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter {

       var view= LayoutInflater.from(parent.context).inflate(R.layout.data_item,null,false)
        var myAdapter=MyAdapter(view)
        return myAdapter
    }

    override fun onBindViewHolder(holder: MyAdapter, position: Int) {
        holder.id.setText(list[position].Student_id.toString())
        holder.name.setText(list[position].name)
        holder.mobile.setText(list[position].mobile)
        holder.gender.setText(list[position].gender)
        holder.fees.setText(list[position].fees.toString())
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(list: ArrayList<DataModelClass>) {
        this.list=list
        notifyDataSetChanged()
    }
}