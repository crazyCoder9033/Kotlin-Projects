package com.example.whatsappchat

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsappchat.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var sharedPreferences: SharedPreferences
    var list = ArrayList<chatsModelClass>()
     var getId=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        workingClass()
    }

    private fun workingClass() {
        sharedPreferences=getSharedPreferences("MySharedPref", MODE_PRIVATE)

         getId=sharedPreferences.getString("email","").toString().toInt()
//        Log.e("TAG", "workingClass: "+getId )

        firebaseDatabase = FirebaseDatabase.getInstance()

        binding.txtSend.setOnClickListener {
            var msg = binding.edtMsg.text.toString()

            val key = firebaseDatabase.reference.child("ChatsTb").push().key ?: ""

            var obj = chatsModelClass(msg,getId)

            firebaseDatabase.reference.child("ChatsTb").child(key).setValue(obj).addOnCompleteListener {
                if (it.isSuccessful)
                {
                    binding.edtMsg.text.clear()

                    firebaseDatabase.reference.child("ChatsTb").addValueEventListener(object : ValueEventListener{

                        override fun onDataChange(snapshot: DataSnapshot) {
                            list.clear()
                            for (i in snapshot.children) {
                                var data = i.getValue(chatsModelClass::class.java)

                                data?.let { it1 -> list.add(it1) }


                                var adapter = ChatsAdapter(list,getId)
                                var Manager = LinearLayoutManager(this@MainActivity,
                                    LinearLayoutManager.VERTICAL,
                                    false)
                                binding.rcvChats.layoutManager = Manager
                                binding.rcvChats.adapter = adapter
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                        }

                    })

                }
            }.addOnFailureListener {

            }
        }

    }
}