package com.example.recyclerviewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountryCodeAdapter(var country: ArrayList<CountryModelClass>, var n: ((Int,String)-> Unit) ) : RecyclerView.Adapter<CountryCodeAdapter.MyView>() {
    class MyView(view : View) : RecyclerView.ViewHolder(view) {
     var txtCode : TextView=view.findViewById(R.id.txtCode)
        var txtName: TextView=view.findViewById(R.id.txtName)
        var loutCountry:LinearLayout=view.findViewById(R.id.loutCountry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.country_item,parent,false)
        var myview=MyView(view)

    return myview}

    override fun onBindViewHolder(holder: MyView, position: Int) {
          holder.txtCode.setText(country[position].code.toString())
        holder.txtName.setText(country[position].name)
        holder.loutCountry.setOnClickListener {
            n.invoke(country[position].code,country[position].name)
        }

    }

    override fun getItemCount(): Int {
        return country.size
    }
}