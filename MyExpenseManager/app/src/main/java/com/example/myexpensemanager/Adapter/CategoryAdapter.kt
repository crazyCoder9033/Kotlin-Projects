package com.example.myexpensemanager.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myexpensemanager.ModelClass.CategoryModelClass
import com.example.myexpensemanager.R

class CategoryAdapter(var listofTypes: ArrayList<CategoryModelClass>,var n: ((String)-> Unit)) : RecyclerView.Adapter<CategoryAdapter.MyAdapter>() {
    var pos=-1
    class MyAdapter(view : View) : RecyclerView.ViewHolder(view){
        var selected: RadioButton=view.findViewById(R.id.rBtn)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.rcv_category_item,parent,false)
        var vv= MyAdapter(v)
        return vv
    }
    override fun onBindViewHolder(holder: MyAdapter, position: Int) {
        holder.selected.setText(listofTypes[position].categoryname)
        holder.selected.setOnClickListener {
            n.invoke(listofTypes[position].categoryname)
            pos=position
            notifyDataSetChanged()
        }
        if (position==pos)
        {
            holder.selected.isChecked=true
        }
        else{
            holder.selected.isChecked=false
        }
    }
    override fun getItemCount(): Int {
        return listofTypes.size
    }



}