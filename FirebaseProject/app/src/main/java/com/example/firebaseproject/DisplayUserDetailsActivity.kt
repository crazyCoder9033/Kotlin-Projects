package com.example.firebaseproject

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseproject.databinding.ActivityDisplayUserDetailsBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DisplayUserDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDisplayUserDetailsBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDisplayUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        workingClass()
    }

    private fun workingClass() {


        sharedPreferences=getSharedPreferences("MySharedPref", MODE_PRIVATE)

        binding.txtName.text=sharedPreferences.getString("name","")
        binding.txtEmail.text=sharedPreferences.getString("email","")
        binding.txtAddress.text=sharedPreferences.getString("address","")
        binding.txtPhone.text=sharedPreferences.getString("phone","")

        auth=FirebaseAuth.getInstance()

        binding.btnLogout.setOnClickListener {
            var myEdit : SharedPreferences.Editor=sharedPreferences.edit()
            myEdit.remove("isLogin")
            myEdit.commit()
            auth.signOut()
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        }


        }

}