package com.example.myexpensemanager.Activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myexpensemanager.Adapter.AddCategoryAdapter
import com.example.myexpensemanager.Adapter.CategoryAdapter
import com.example.myexpensemanager.CategoryHelper
import com.example.myexpensemanager.ModeAdapter
import com.example.myexpensemanager.ModelClass.CategoryModelClass
import com.example.myexpensemanager.TransactionActivity
import com.example.myexpensemanager.databinding.ActivityAddIncomeBinding
import com.example.myexpensemanager.databinding.DialogBinding
import com.example.myexpensemanager.databinding.ModeRecycleItemBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class AddIncomeExpActivity : AppCompatActivity() {
    var listofTypes = ArrayList<CategoryModelClass>()
    lateinit var binding: ActivityAddIncomeBinding
    var mode = ArrayList<String>()
    var selectedcategory = ""
    var selectedMode = ""
    lateinit var xx: CategoryHelper
    var type = -1
    var dateValue = ""
    var name = String
    var id_number=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddIncomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var name: String? = intent.getStringExtra("title")
        binding.txtTitle.text = name
        xx = CategoryHelper(this)
        working()
    }
    private fun working() {

            if (intent != null && intent.hasExtra("updateRecord")) {
                var newAmt: String? = intent.getStringExtra("amount")
                var newNote: String? = intent.getStringExtra("note")
                var icon: String? = intent.getStringExtra("key_icon")
                var category: String? = intent.getStringExtra("category")
                var mode: String? = intent.getStringExtra("mode")
                id_number= intent.getIntExtra("id",0)
                binding.txtDone.text=icon
                binding.edtAmount.setText(newAmt)
                binding.edtNote.setText(newNote)

            }

        binding.txtDone.setOnClickListener {
            var amount = binding.edtAmount.text.toString()
            var notes = binding.edtNote.text.toString()

            if (amount.isEmpty()) {
                Toast.makeText(this, "please enter amount", Toast.LENGTH_SHORT).show()
            } else if (amount.length <= 1 || amount.length >= 10) {
                Toast.makeText(this, "please enter a amount", Toast.LENGTH_SHORT).show()
            } else if (notes.isEmpty()) {
                Toast.makeText(this, "please enter a Note", Toast.LENGTH_SHORT).show()
            } else if (notes.length <= 1 || notes.length >= 10) {
                Toast.makeText(this, "please enter a Note", Toast.LENGTH_SHORT).show()
            } else if (notes.isEmpty()) {
                Toast.makeText(this, "please enter a Note", Toast.LENGTH_SHORT).show()
            } else if (notes.length <= 1 || notes.length >= 20) {
                Toast.makeText(this, "please enter a Note", Toast.LENGTH_SHORT).show()
            } else {

                if (binding.rdgType.checkedRadioButtonId == -1) {

                } else{
                    val selectId: Int = binding.rdgType.checkedRadioButtonId
                    var selectedRadioButton: RadioButton = findViewById(selectId)
                    var text = selectedRadioButton.text.toString()

                    if (text.equals("Income")) {
                        type = 1
                    } else {
                        type = 2
                    }
                        Log.e("TAG", "working: "+binding.txtDone.text.toString())
                        if (binding.txtDone.text.toString().equals("update")){
                            xx.updateRecord(amount, selectedcategory,selectedMode,type,notes,id_number)
                            Log.e("TAG", "working "+amount+"" )

                    }

                    else {
                        xx.insertExpenseIncomeRecord(
                            dateValue,
                            amount,
                            selectedcategory,
                            selectedMode,
                            type,
                            notes)
                        Log.e("TAG", "working: "+dateValue )
                    }

                }
            }

                val trans = Intent(this@AddIncomeExpActivity, TransactionActivity::class.java)
                startActivity(trans)


        }
            val simlpeDateFormat = SimpleDateFormat("dd-MM-yyyy")
            val date: String = simlpeDateFormat.format(Date())
            binding.txtDate.text = date

            dateValue = date

            val simlpeTimeFormat = SimpleDateFormat("hh : mm")
            val time: String = simlpeTimeFormat.format(Date())
            binding.txtTime.text = time



            binding.imgBack.setOnClickListener {
                val b = Intent(this@AddIncomeExpActivity, HomePageActivity::class.java)
                startActivity(b)
                finish()
            }

        binding.loutCategory.setOnClickListener {
                val dialog = Dialog(this)
                val dialogBinding: DialogBinding = DialogBinding.inflate(layoutInflater)
                dialog.setContentView(dialogBinding.root)
                listofTypes = xx.displayRecord()
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.window?.setLayout(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                var adapter = CategoryAdapter(listofTypes) { categoryname ->
                    Log.e("TAG", "working : "+categoryname)
                    selectedcategory = categoryname
                }

            var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                dialogBinding.rcvCategory.layoutManager = manager
                dialogBinding.rcvCategory.adapter = adapter

                dialogBinding.btnCategoryCancel.setOnClickListener {
                    Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }

                dialogBinding.btnCategorySelect.setOnClickListener {
                    Toast.makeText(this, "DONE", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()

                }
                dialog.show()
            }
        binding.loutMOde.setOnClickListener {
                val d = Dialog(this)
                val db: ModeRecycleItemBinding = ModeRecycleItemBinding.inflate(layoutInflater)
                d.setContentView(db.root)
                d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                d.window?.setLayout(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                db.btnModeSelect.setOnClickListener {
                    Toast.makeText(this, "DONE", Toast.LENGTH_SHORT).show()
                    d.dismiss()
                }

                db.btnModeCancel.setOnClickListener {
                    d.dismiss()
                }
                d.show()

                var adapter = ModeAdapter(mode) { mode ->
                    selectedMode = mode
                    Log.e("TAG", "working: " + mode)
                }
            var modeManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                db.rcvMode.layoutManager = modeManager
                db.rcvMode.adapter = adapter
            }

        mode.add("CASH")
        mode.add("CREDIT CARD")
        mode.add("DEBIT CARD")
        mode.add("UPI")
        mode.add("NET BANKING")
        mode.add("CHEQUE")


    }


}