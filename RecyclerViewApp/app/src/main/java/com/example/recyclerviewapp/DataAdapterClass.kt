package com.example.recyclerviewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapterClass(var list: ArrayList<DataModelClass>, var ii: DataInterface) : RecyclerView.Adapter<DataAdapterClass.MyView>(){
    class MyView(view: View) : RecyclerView.ViewHolder(view) {
        var txtId:  TextView=view.findViewById(R.id.txtId)
        var txtName:  TextView=view.findViewById(R.id.txtName)
        var loutClick: LinearLayout=view.findViewById(R.id.loutClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
      var view=LayoutInflater.from(parent.context).inflate(R.layout.item_file,parent,false)
        var myView=MyView(view)
        return myView
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
      holder.txtId.setText(list[position].id.toString())
        holder.txtName.setText(list[position].name.toString())
        holder.loutClick.setOnClickListener {
            ii.onclick(list[position].id,list[position].name)
        }

    }

    override fun getItemCount(): Int {
       return list.size
    }
}