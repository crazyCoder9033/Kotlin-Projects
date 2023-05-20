package com.example.aaglagadenge

import android.content.Intent
import android.net.Uri
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.aaglagadenge.databinding.ActivityUpdateBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UpdateActivity : AppCompatActivity() {
    lateinit var binding : ActivityUpdateBinding
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var adapter : Adapter
    var key = ""
    lateinit var imageLink : String
    var studentList = ArrayList<StudentModelClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseDatabase = FirebaseDatabase.getInstance()

        workingClass()
    }

    private fun workingClass() {

        binding.edtName.setText(intent.getStringExtra("name"))
        binding.edtAddress.setText(intent.getStringExtra("address"))
        binding.edtPhone.setText(intent.getStringExtra("phone"))
        binding.edtEmail.setText(intent.getStringExtra("email"))

        key = intent.getStringExtra("key").toString()

        binding.btnUpdate.setOnClickListener {

            var name = binding.edtName.text.toString()
            var address = binding.edtAddress.text.toString()
            var phone = binding.edtPhone.text.toString()
            var email = binding.edtEmail.text.toString()

//            val key = firebaseDatabase.reference.child("StudentTb").push().key ?: ""
            val obj = StudentModelClass(key, name, address, phone, email,imageLink)
            firebaseDatabase.reference.child("StudentTb").child(key).setValue(obj)
                .addOnCompleteListener {

                    if (it.isSuccessful) {
                        binding.edtName.text.clear()
                        binding.edtAddress.text.clear()
                        binding.edtPhone.text.clear()
                        binding.edtEmail.text.clear()
                        Toast.makeText(this, "Record Updated Successfully ", Toast.LENGTH_SHORT)
                            .show()

                    }
                }.addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

            var intent=Intent(this,DisplayDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}