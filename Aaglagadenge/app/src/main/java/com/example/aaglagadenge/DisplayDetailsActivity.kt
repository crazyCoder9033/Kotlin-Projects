package com.example.aaglagadenge

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aaglagadenge.databinding.ActivityDisplayDetailsBinding
import com.example.aaglagadenge.databinding.DeleteItemBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DisplayDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDisplayDetailsBinding
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var adapter : Adapter
    var id = ""
    var studentList = ArrayList<StudentModelClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        workingClass()
    }

    private fun workingClass() {

        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
        firebaseDatabase.reference.child("StudentTb").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                studentList.clear()
                for (i in snapshot.children) {
                    var data = i.getValue(StudentModelClass::class.java)
                    Log.e("TAG", "onDataChange: " + data?.email + "" + data?.fullName)
                    data?.let { it1 -> studentList.add(it1) }
                }
                setAdapter()
                adapter.updateRecord(studentList)
            }
            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

    private fun setAdapter() {
        adapter = Adapter(this,{
            var intent = Intent(this,UpdateActivity::class.java)
            intent.putExtra("name",it.fullName)
            intent.putExtra("key",it.key)
            intent.putExtra("address",it.address)
            intent.putExtra("phone",it.phone)
            intent.putExtra("email",it.email)
            startActivity(intent)
        },{
            id=it

            val dialog = Dialog(this)
            val dialogDelete: DeleteItemBinding = DeleteItemBinding.inflate(layoutInflater)
            dialog.setContentView(dialogDelete.root)

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            dialogDelete.btnDeleteSelect.setOnClickListener {
                deleteRecord()
                dialog.dismiss()
            }
            dialogDelete.btnDeleteCancel.setOnClickListener {
                Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            dialog.show()


        })
        var Manager = LinearLayoutManager(this@DisplayDetailsActivity,
            LinearLayoutManager.VERTICAL,
            false)
        binding.rcvDisplay.layoutManager = Manager
        binding.rcvDisplay.adapter = adapter
    }

    private fun deleteRecord() {

        firebaseDatabase.reference.child("StudentTb").child(id).removeValue().addOnCompleteListener {
            if (it.isSuccessful)
            {
                Toast.makeText(this, "Record deleted successfully", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }
}