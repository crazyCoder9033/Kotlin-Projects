package com.example.whatsappchat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ChatsAdapter(var list: ArrayList<chatsModelClass>, var getId: Int) :RecyclerView.Adapter<ChatsAdapter.myAdapter>() {

    class myAdapter(view : View) : RecyclerView.ViewHolder(view) {

        var msg :TextView=view.findViewById(R.id.txtMessage)
//        var mail :TextView=view.findViewById(R.id.txtmail)
//        var leftmsg:TextView=view.findViewById(R.id.leftTxtMessage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myAdapter {

        if (viewType== getId)
        {
            var v= LayoutInflater.from(parent.context).inflate(R.layout.chat_item,parent,false)
            var vv=myAdapter(v)
            return vv
        }

        else{
            var v= LayoutInflater.from(parent.context).inflate(R.layout.left_chat_item,parent,false)
            var vv=myAdapter(v)
            return vv
        }


    }

    override fun onBindViewHolder(holder: myAdapter, position: Int) {
        holder.msg.setText(list[position].chats)
//        holder.leftmsg.setText(list[position].chats)

    }

    override fun getItemCount(): Int {
       return list.size
    }
}