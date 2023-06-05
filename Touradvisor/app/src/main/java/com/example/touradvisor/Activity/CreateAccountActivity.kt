package com.example.touradvisor.Activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.touradvisor.databinding.ActivityCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class CreateAccountActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateAccountBinding
    private lateinit var auth: FirebaseAuth
    lateinit var sharedPreferences: SharedPreferences
    lateinit var firebaseDatabase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        sharedPreferences=getSharedPreferences("MySharedPref", MODE_PRIVATE)
        workingClass()
    }

    private fun workingClass() {

        binding.btnCreateAccount.setOnClickListener {
            var name= binding.edtName.text.toString()
            var age= binding.edtAge.text.toString()
            var address= binding.edtAddress.text.toString()
            var city= binding.edtCity.text.toString()
            var phone= binding.edtPhone.text.toString()
            var email= binding.edtEmail.text.toString()
            var password= binding.edtPassword.text.toString()

            if (name.isEmpty())
            {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }
            else if(age.isEmpty())
            {
                Toast.makeText(this, "please enter your age", Toast.LENGTH_SHORT).show()
            }
            else if(address.isEmpty())
            {
                Toast.makeText(this, "please enter your address", Toast.LENGTH_SHORT).show()
            }

            else if(city.isEmpty())
            {
                Toast.makeText(this, "please enter your city", Toast.LENGTH_SHORT).show()
            }
            else if(phone.isEmpty())
            {
                Toast.makeText(this, "please enter your phone", Toast.LENGTH_SHORT).show()
            }
            else if(email.isEmpty())
            {
                Toast.makeText(this, "please enter your email", Toast.LENGTH_SHORT).show()
            }
           else
            {
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{

                    if (it.isSuccessful)
                    {
                        Toast.makeText(this, "created account", Toast.LENGTH_SHORT).show()
                        var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
                        myEdit.putBoolean("isLogin", true)
//                        myEdit.putString("name",name)
//                        myEdit.putString("email",email)
//                        myEdit.putString("age",age)
//                        myEdit.putString("city",city)
//                        myEdit.putString("address",address)
//                        myEdit.putString("phone",phone)
                        myEdit.commit()
                        var intent = Intent(this, DashBoardActivity::class.java)
                        startActivity(intent)
                        finish()

                        addUserData(name,age,address,city,phone,email,auth.currentUser?.uid!!)
                    }
                }.addOnFailureListener {
                    Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }

            }


        }
    }

    private fun addUserData(name: String, age: String, address: String, city: String, phone: String, email: String, uid: String) {
        firebaseDatabase=FirebaseDatabase.getInstance().getReference()

        firebaseDatabase.child("user").child(uid).setValue(userModelClass(name,age,address,city,phone,email,uid))


    }


}
class userModelClass(
    var  name: String,
    var  age: String,
    var address: String,
    var  city: String,
    var phone: String,
    var  email: String,
    var uid: String?,
) {


}