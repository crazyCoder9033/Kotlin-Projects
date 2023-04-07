package com.example.myexpensemanager.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myexpensemanager.ReportsActivity
import com.example.myexpensemanager.TransactionActivity
import com.example.myexpensemanager.databinding.ActivityHomePageBinding


class HomePageActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomePageBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intents()
    }

    private fun intents() {

        var titleIncome="Add Income"
        binding.cdAddIncome.setOnClickListener {
            var income = Intent(this@HomePageActivity, AddIncomeExpActivity::class.java)
            income.putExtra("title",titleIncome)
            startActivity(income)
        }

        var titleExpense="Add Expense"
        binding.cdAddExpenses.setOnClickListener {
            var expense = Intent(this@HomePageActivity, AddIncomeExpActivity::class.java)
            expense.putExtra("title",titleExpense)
            startActivity(expense)
        }

        binding.cdAllTransaction.setOnClickListener {
            val k = Intent(this@HomePageActivity, TransactionActivity::class.java)
            startActivity(k)
        }

        binding.cdReports.setOnClickListener {
            val l = Intent(this@HomePageActivity, ReportsActivity::class.java)
            startActivity(l)
        }

        binding.cdAddCategory.setOnClickListener {
            val m = Intent(this@HomePageActivity, AddCategoryAcitivity::class.java)
            startActivity(m)
        }

    }
}

