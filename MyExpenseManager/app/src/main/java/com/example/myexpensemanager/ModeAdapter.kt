package com.example.myexpensemanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView

class ModeAdapter(var mode: ArrayList<String>,var invo: ((String)-> Unit)) : RecyclerView.Adapter<ModeAdapter.myView>() {
    var pos=-1
    class myView(view : View):RecyclerView.ViewHolder(view) {
        var rbMode:RadioButton=view.findViewById(R.id.rbMode)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myView {
       var v=LayoutInflater.from(parent.context).inflate(R.layout.mode_item,parent,false)
        var view=myView(v)
        return view
    }
    override fun onBindViewHolder(holder: myView, position: Int) {
        holder.rbMode.setText(mode[position])
        holder.rbMode.setOnClickListener {
            invo.invoke(mode[position])
            pos=position
            notifyDataSetChanged()
        }
        if(position==pos)
        {
            holder.rbMode.isChecked=true
        }
        else
        {
            holder.rbMode.isChecked=false
        }
    }

    override fun getItemCount(): Int {
       return mode.size
    }
}