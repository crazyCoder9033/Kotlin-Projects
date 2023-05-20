package com.example.aaglagadenge

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter(var context: Context,var updateInvoke : ((StudentModelClass)-> Unit), var deleteInvoke:((String)-> Unit)):RecyclerView.Adapter<Adapter.myAdapter>() {
    var studentList=ArrayList<StudentModelClass>()
    class myAdapter(view : View) : RecyclerView.ViewHolder(view){
        var txtFullName : TextView=view.findViewById(R.id.txtFullName)
        var txtAddress : TextView=view.findViewById(R.id.txtAddress)
        var txtPhone : TextView=view.findViewById(R.id.txtPhone)
        var txtEmail : TextView=view.findViewById(R.id.txtEmail)
        var imgEdit :ImageView=view.findViewById(R.id.imgEdit)
        var imgDelete :ImageView=view.findViewById(R.id.imgDelete)
        var imgDisplay: ImageView=view.findViewById(R.id.imgDisplay)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myAdapter {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.rcv_details_display,parent,false)
        var vv = myAdapter(v)
        return vv
    }

    override fun onBindViewHolder(holder: myAdapter, position: Int) {
       holder.txtFullName.setText(studentList[position].fullName)
       holder.txtAddress.setText(studentList[position].address)
       holder.txtPhone.setText(studentList[position].phone)
       holder.txtEmail.setText(studentList[position].email)
        Glide.with(context).load(studentList[position].images).into(holder.imgDisplay)



        holder.imgEdit.setOnClickListener {
            updateInvoke.invoke(studentList[position])
        }
holder.imgDelete.setOnClickListener {
    deleteInvoke.invoke(studentList[position].key)
}
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun updateRecord(
        studentList: java.util.ArrayList<StudentModelClass>
    ) {

        this.studentList= ArrayList()
        this.studentList=studentList
        notifyDataSetChanged()
    }


}