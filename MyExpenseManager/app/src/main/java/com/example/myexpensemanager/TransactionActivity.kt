package com.example.myexpensemanager

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myexpensemanager.Activity.AddIncomeExpActivity
import com.example.myexpensemanager.Activity.HomePageActivity
import com.example.myexpensemanager.ModelClass.IncomeExpenseModelClass
import com.example.myexpensemanager.databinding.ActivityTransactionBinding
import com.example.myexpensemanager.databinding.DeleteItemBinding

class TransactionActivity : AppCompatActivity() {
     lateinit var callingDisplayFunction:CategoryHelper
    lateinit var bindin : ActivityTransactionBinding
    lateinit var adapter:TransactionAdapter
    var incomeExpenselist=ArrayList<IncomeExpenseModelClass>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindin= ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(bindin.root)

        callingDisplayFunction= CategoryHelper(this)
        intview()
    }

    private fun intview() {

        incomeExpenselist= callingDisplayFunction.displayIncomeExpenseRecord()
         adapter=TransactionAdapter(total = {

             var amountIncome=adapter.IncomeAmount()

             var amountExpense=adapter.ExpenseAmount()
             Log.e("TAG", "gggggg: "+amountIncome+"--"+amountExpense )
             var amountTotal=amountIncome-amountExpense

             bindin.txtIncome.setTextColor(Color.GREEN)
             bindin.txtExpense.setTextColor(Color.RED)
             bindin.txtTotal.setTextColor(Color.BLUE)
             bindin.txtIncome.text=amountIncome.toString()
             bindin.txtExpense.text=amountExpense.toString()
             bindin.txtTotal.text= amountTotal.toString()

         },incomeExpenselist,{
            var titleUpdate="Update Data"
            var iconUpdate="update"
            val transaction = Intent(this@TransactionActivity, AddIncomeExpActivity::class.java)
            transaction.putExtra("id",+it.id)
            transaction.putExtra("type", it.type)
            transaction.putExtra("amount", it.amount)
            transaction.putExtra("note", it.note)
             transaction.putExtra("title",titleUpdate)
            transaction.putExtra("key_icon",iconUpdate)
            transaction.putExtra("updateRecord",true)
            startActivity(transaction)

        })  {
             val dialog = Dialog(this)
             val dialogDelete: DeleteItemBinding = DeleteItemBinding.inflate(layoutInflater)
             dialog.setContentView(dialogDelete.root)

             dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
             dialog.window?.setLayout(
                 LinearLayout.LayoutParams.MATCH_PARENT,
                 LinearLayout.LayoutParams.WRAP_CONTENT
             )

             dialogDelete.btnDeleteCancel.setOnClickListener {
                 Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
                 dialog.dismiss()
             }

             dialogDelete.btnDeleteSelect.setOnClickListener {
                 incomeExpenselist=callingDisplayFunction.displayIncomeExpenseRecord()
                 adapter.updatedData(incomeExpenselist)
                 Toast.makeText(this, "DELETED", Toast.LENGTH_SHORT).show()
                 dialog.dismiss()

             }
             dialog.show()

             callingDisplayFunction.deleteRecord(it)

        }


        var transManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        bindin.rcvTransaction.layoutManager = transManager
        bindin.rcvTransaction.adapter = adapter

        incomeExpenselist=callingDisplayFunction.displayIncomeExpenseRecord()
        adapter.updatedData(incomeExpenselist)

        bindin.imgDoneTransaction.setOnClickListener {
            val transaction = Intent(this@TransactionActivity, HomePageActivity::class.java)
            startActivity(transaction)
        }

        bindin.imgBackTransaction.setOnClickListener {
            val transaction = Intent(this@TransactionActivity, HomePageActivity::class.java)
            startActivity(transaction)
        }




    }
}