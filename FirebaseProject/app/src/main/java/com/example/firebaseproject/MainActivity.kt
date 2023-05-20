package com.example.firebaseproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseproject.databinding.ActivityMainBinding
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.json.JSONObject
import java.util.*


class MainActivity : AppCompatActivity() {
     lateinit var callbackManager: CallbackManager
    lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
     val EMAIL = "email"
    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        workingClass()
    }

    private fun workingClass() {
        sharedPreferences=getSharedPreferences("MySharedPref", MODE_PRIVATE)


        if (sharedPreferences.getBoolean("isLogin",false)== true)
        {
            var intent = Intent(this@MainActivity,DisplayUserDetailsActivity::class.java)
            startActivity(intent)
        }
            binding.btnCreateAccount.setOnClickListener {
                var name = binding.edtName.text.toString()
                var email = binding.edtEmail.text.toString()
                var password = binding.edtPassword.text.toString()
                var address = binding.edtAddress.text.toString()
                var phone = binding.edtPhone.text.toString()

                if (name.isEmpty())
                {
                    Toast.makeText(this, "please enter your name", Toast.LENGTH_SHORT).show()
                }

                else if (address.isEmpty())
                {
                    Toast.makeText(this, "please enter address", Toast.LENGTH_SHORT).show()
                }

                else if (email.isEmpty())
                {
                    Toast.makeText(this, "please enter a valid email", Toast.LENGTH_SHORT).show()
                }
                else if (password.isEmpty())
                {
                    Toast.makeText(this, "please enter a password", Toast.LENGTH_SHORT).show()
                }

                else
                {
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{

                    if (it.isSuccessful)
                    {
                        Toast.makeText(this, "created account", Toast.LENGTH_SHORT).show()
                        var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
                        myEdit.putBoolean("isLogin", true)
                        myEdit.putString("name",name)
                        myEdit.putString("email",email)
                        myEdit.putString("address",address)
                        myEdit.putString("phone",phone)
                        myEdit.commit()
                        var intent = Intent(this,DisplayUserDetailsActivity::class.java)
                        startActivity(intent)

                    }
                }.addOnFailureListener {
                    Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }


        binding.btnLogin.setOnClickListener {
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("423656113999-176n8qrjahq5553shje2k2isegapf296.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        binding.btnGoogleLogin.setOnClickListener {
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, 100)
        }


//       facebook loin
        auth= FirebaseAuth.getInstance()
        callbackManager = CallbackManager.Factory.create();
        binding.loginButton.setReadPermissions(Arrays.asList(EMAIL));
        binding.loginButton.registerCallback(callbackManager,object : FacebookCallback<LoginResult>{
            override fun onCancel() {

            }

            override fun onError(error: FacebookException) {

            }

            override fun onSuccess(result: LoginResult) {

//                used for other details.. if hume chaiye toh
//
//                var request=GraphRequest.newMeRequest(result.accessToken,object : GraphRequest.GraphJSONObjectCallback{
//                    override fun onCompleted(obj: JSONObject?, response: GraphResponse?) {
//
//                        var email = obj?.getString("email")
//                        Log.e("TAG", "onCompleted: "+email )
//                    }
//                })
//                val parameters = Bundle()
//                parameters.putString("fields","id,name,email,birthday")
//                request.parameters=parameters
//                request.executeAsync()

                Log.e("TAG", "onSuccess: "+result.accessToken )
                var credential:AuthCredential=FacebookAuthProvider.getCredential(result.accessToken.token)
                auth=FirebaseAuth.getInstance()

                auth.signInWithCredential(credential).addOnCompleteListener {
                    if (it.isSuccessful)
                    {
                        var intent = Intent(this@MainActivity,DisplayUserDetailsActivity::class.java)
                        var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
                        myEdit.putBoolean("isLogin", true)
                        myEdit.commit()
                        startActivity(intent)
//                        startActivity(Intent(this@MainActivity,DisplayUserDetailsActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
//                        Toast.makeText(this@MainActivity, "Facebook login successfully", Toast.LENGTH_SHORT).show()

                    }
                }.addOnFailureListener {
                    Toast.makeText(this@MainActivity, "something went wrong", Toast.LENGTH_SHORT).show()
                }

            }


        })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)

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
                    val googleSignInAccount: GoogleSignInAccount = signInAccountTask.getResult(ApiException::class.java)
                    // Check condition
                    if (googleSignInAccount != null) {
                        // When sign in account is not equal to null initialize auth credential
                        val authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
                        // Check credential
                        auth.signInWithCredential(authCredential)
                            .addOnCompleteListener(this,
                                OnCompleteListener<AuthResult?> { task ->
                                    if (task.isSuccessful) {
                                        startActivity(Intent(this@MainActivity,
                                            DisplayUserDetailsActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

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
}