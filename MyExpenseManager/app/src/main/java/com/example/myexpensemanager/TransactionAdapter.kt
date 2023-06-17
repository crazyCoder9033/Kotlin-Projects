package com.example.myexpensemanager

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myexpensemanager.ModelClass.IncomeExpenseModelClass

class TransactionAdapter(var total : (() -> Unit),var incomeExpenselist: ArrayList<IncomeExpenseModelClass>,var invo: ((IncomeExpenseModelClass)-> Unit),var delete: ((Int) -> Unit)) : RecyclerView.Adapter<TransactionAdapter.myAdapter>() {
   var incomeAmt=0
    var expenseAmt=0

    var  totalex=0
    var totalinc=0
    class myAdapter(view : View) :RecyclerView.ViewHolder(view){

        var txtIncomeExpense:TextView=view.findViewById(R.id.txtIncomeExpense)
        var txtDate:TextView=view.findViewById(R.id.txtDate)
        var txtAmount:TextView=view.findViewById(R.id.txtAmount)
        var txtNote:TextView=view.findViewById(R.id.txtNote)
        var txtCategoryType:TextView=view.findViewById(R.id.txtCategoryType)
        var txtMode:TextView=view.findViewById(R.id.txtMode)
        var imgEdit:ImageView=view.findViewById(R.id.imgEdit)
        var imgDelete:ImageView=view.findViewById(R.id.imgDelete)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myAdapter {
        var v=LayoutInflater.from(parent.context).inflate(R.layout.transaction_recycler_item,parent,false)
        var view=myAdapter(v)
        return view
    }
    override fun onBindViewHolder(holder: myAdapter, position: Int) {
        holder.txtDate.setText(incomeExpenselist[position].date)
        holder.txtAmount.setText(incomeExpenselist[position].amount)
        holder.txtCategoryType.setText(incomeExpenselist[position].category)
        holder.txtMode.setText(incomeExpenselist[position].mode)
        holder.txtIncomeExpense.setText(incomeExpenselist[position].type.toString())



        if (holder.txtIncomeExpense.text.toString()=="1")
        {
            holder.txtIncomeExpense.setBackgroundColor(Color.GREEN)
            Log.e("TAG", "green: "+holder.txtIncomeExpense.text.toString() )

             var incomeAmt1=holder.txtAmount.text.toString()
//            incomeAmt=incomeAmt1.toInt()
//            Log.e("TAG", "income: "+incomeAmt )


            totalinc=totalinc+incomeAmt1.toInt()
            Log.e("TAG", "iinnnnn: =="+totalinc )
        }
        else
        {

            holder.txtIncomeExpense.setBackgroundColor(Color.RED)
            var incomeExp1=holder.txtAmount.text.toString()
            expenseAmt=incomeExp1.toInt()


            Log.e("TAG", "red: "+holder.txtIncomeExpense.text.toString() )
            totalex=totalex+incomeExp1.toInt()
            Log.e("TAG", "exxpppp: "+totalex )
        }
        holder.txtNote.setText(incomeExpenselist[position].note)

        holder.imgEdit.setOnClickListener {
            invo.invoke(incomeExpenselist[position])
        }
        holder.imgDelete.setOnClickListener {
            delete.invoke(incomeExpenselist[position].id)
            Log.e("TAG", "delete ")
        }
        if(position == incomeExpenselist.size-1)
        {
            total.invoke()
        }
    }
    override fun getItemCount(): Int {
        return incomeExpenselist.size
    }

    fun updatedData(incomeExpenselist: ArrayList<IncomeExpenseModelClass>) {
        this.incomeExpenselist= ArrayList()
        this.incomeExpenselist.addAll(incomeExpenselist)
        notifyDataSetChanged()
    }


    fun IncomeAmount(): Int {

//            var incomeAmount=incomeAmt

        Log.e("TAG", "IncomeAmount: "+totalinc)
        return totalinc
    }

    fun ExpenseAmount(): Int {

        Log.e("TAG", "exxx: "+totalinc)


        return totalex
    }
}