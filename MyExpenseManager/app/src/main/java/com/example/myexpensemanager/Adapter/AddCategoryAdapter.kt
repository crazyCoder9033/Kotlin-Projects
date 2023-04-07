package com.example.myexpensemanager.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myexpensemanager.ModelClass.AddModelClass
import com.example.myexpensemanager.R

class AddCategoryAdapter(var list: ArrayList<AddModelClass>) : RecyclerView.Adapter<AddCategoryAdapter.Myview>() {
    class Myview(vi: View) : RecyclerView.ViewHolder(vi) {
        var txtView:TextView=vi.findViewById(R.id.txtView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview {
        var v=LayoutInflater.from(parent.context).inflate(R.layout.rcv_add_item,parent,false)
        var vv= Myview(v)
        return vv
    }

    override fun onBindViewHolder(holder: Myview, position: Int) {
        holder.txtView.setText(list[position].xyz)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(list: ArrayList<AddModelClass>) {
        this.list= list
        notifyDataSetChanged()
    }


}