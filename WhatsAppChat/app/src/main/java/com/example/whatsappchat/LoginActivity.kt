package com.example.whatsappchat

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.whatsappchat.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        workingClass()
    }

    private fun workingClass() {
        sharedPreferences=getSharedPreferences("MySharedPref", MODE_PRIVATE)

        if (sharedPreferences.getBoolean("isLogin",false)== true)
        {
            var intent = Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            var email= binding.edtEmail.text.toString()
            var password= binding.edtPassword.text.toString()

            if (email.isEmpty())
            {
                Toast.makeText(this, "please enter a valid email", Toast.LENGTH_SHORT).show()
            }
            else if (password.isEmpty())
            {
                Toast.makeText(this, "please enter a password", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                    if (it.isSuccessful)
                    {
                        Log.e("TAG", "workingClass: "+it)
                        Toast.makeText(this, "successfully logged in", Toast.LENGTH_SHORT).show()
                        var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
                        myEdit.putBoolean("isLogin", true)
                        myEdit.putString("email",email)
                        myEdit.commit()

                        var intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }

                }.addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}