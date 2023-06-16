package com.example.touradvisor.Activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.touradvisor.databinding.ActivityLoginScreenBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginScreenBinding
    private lateinit var auth: FirebaseAuth

    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        sharedPreferences=getSharedPreferences("MySharedPref", MODE_PRIVATE)
        workingClass()
        googleLogin()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 100) {
            // When request code is equal to 100 initialize task
            val signInAccountTask: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            // check condition
            if (signInAccountTask.isSuccessful()) {
                // When google sign in successful initialize string
                val s = "Google sign in successful"
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show()

                try {
                    // Initialize sign in account
                    val googleSignInAccount: GoogleSignInAccount = signInAccountTask.getResult(
                        ApiException::class.java)
                    // Check condition
                    if (googleSignInAccount != null) {
                        // When sign in account is not equal to null initialize auth credential
                        val authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
                        // Check credential
                        auth.signInWithCredential(authCredential)
                            .addOnCompleteListener(this,
                                OnCompleteListener<AuthResult?> { task ->
                                    if (task.isSuccessful) {
                                        startActivity(Intent(this@LoginScreenActivity,
                                            DashBoardActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

                                    } else {

                                    }
                                })
                    }
                } catch (e: ApiException) {
                    e.printStackTrace()
                }
            }
        }
    }


    private fun googleLogin() {

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("665533652789-4kcuadkbf7kq1ki2d1941rq3vu28v6th.apps.googleusercontent.com")
            .requestEmail()
            .build()


        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        binding.btnGoogleLogin.setOnClickListener {
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, 100)
        }
    }

    private fun workingClass() {
        if (sharedPreferences.getBoolean("isLogin",false)== true)
        {
            var intent = Intent(this@LoginScreenActivity, DashBoardActivity::class.java)
            startActivity(intent)
            finish()
        }


        binding.btnCreateAccount.setOnClickListener {
            var intent = Intent(this@LoginScreenActivity, CreateAccountActivity::class.java)
            startActivity(intent)
            finish()
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

                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                    if (it.isSuccessful)
                    {
                        Toast.makeText(this, "successfully logged in", Toast.LENGTH_SHORT).show()
                        var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
                        myEdit.putBoolean("isLogin", true)
                        myEdit.putString("email",email)
                        myEdit.commit()
                        var intent = Intent(this, DashBoardActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                }.addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
}