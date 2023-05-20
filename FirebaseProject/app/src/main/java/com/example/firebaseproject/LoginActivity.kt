package com.example.firebaseproject

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseproject.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        workingClass()
    }

    private fun workingClass() {
        sharedPreferences=getSharedPreferences("MySharedPref", MODE_PRIVATE)
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

            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                if (it.isSuccessful)
                {
                    Toast.makeText(this, "successfully logged in", Toast.LENGTH_SHORT).show()
                    var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
                    myEdit.putBoolean("isLogin", true)
                    myEdit.putString("email",email)
                    myEdit.commit()
                    var intent = Intent(this,DisplayUserDetailsActivity::class.java)
                    startActivity(intent)
                }

            }.addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }


        }

    }
}