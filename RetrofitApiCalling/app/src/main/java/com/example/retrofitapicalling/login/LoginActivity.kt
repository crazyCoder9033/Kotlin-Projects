package com.example.retrofitapicalling.login

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitapicalling.DisplayLoginUserDetails
import com.example.retrofitapicalling.apiClient.APIClient
import com.example.retrofitapicalling.apiClient.APIInterface
import com.example.retrofitapicalling.apiClient.LoginResponse
import com.example.retrofitapicalling.databinding.ActivityLoginBinding
import com.example.retrofitapicalling.databinding.ProgressBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    lateinit var apiInterface: APIInterface
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        apiInterface= APIClient.getClient().create(APIInterface::class.java)

        workingClass()
    }


    private fun workingClass() {
        sharedPreferences=getSharedPreferences("MySharedPref", MODE_PRIVATE)




        if (sharedPreferences.getBoolean("isLogin",false)== true)
        {
            var intent = Intent(this@LoginActivity,DisplayLoginUserDetails::class.java)
            startActivity(intent)
        }

        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
       val networkInfo= connectivityManager.activeNetworkInfo
        if (networkInfo !=null && networkInfo.isConnected)
        {

            binding.btnLogin.setOnClickListener {


            val dialog = Dialog(this)
           var progressBinding: ProgressBinding= ProgressBinding.inflate(layoutInflater)
            dialog.setContentView(progressBinding.root)

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            dialog.show()



            var username = binding.edtUsername.text.toString()
            var password = binding.edtPassword.text.toString()

            if (username.isEmpty())
            {
                Toast.makeText(this, "Username is empty", Toast.LENGTH_SHORT).show()
            }

            else if (password.isEmpty())
            {
                Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show()
            }

            else
            {
                apiInterface.loginApiCall(username,password).enqueue(object : Callback<LoginResponse>{
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>,
                    ) {

                        if (response.isSuccessful)
                        {
                            var id= response.body()?.id
                            var email= response.body()?.email
                            var gender= response.body()?.gender
                            var fName=response.body()?.firstName
                            var lName=response.body()?.lastName
                            var username=response.body()?.username
                            var image=response.body()?.image




//                    Log.e("TAG", "onResponse: "+token+id+email+username.toString())
                                var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
                                myEdit.putBoolean("isLogin", true)
                                myEdit.putString("username", binding.edtUsername.text.toString())
                                myEdit.putString("password", binding.edtPassword.text.toString())
                                myEdit.putString("email", email)
                                myEdit.putString("fName", fName)
                                myEdit.putString("lName", lName)
                                myEdit.putString("gender", gender)
                                myEdit.putString("username", username)
                                myEdit.putString("image", image)
                                myEdit.commit()

                                var intent =
                                    Intent(this@LoginActivity, DisplayLoginUserDetails::class.java)
//                            intent.putExtra("id",id)
//                            intent.putExtra("email",email)
//                            intent.putExtra("gender",gender)
//                            intent.putExtra("fName",fName)
//                            intent.putExtra("lName",lName)
//                            intent.putExtra("username",username)
//                            intent.putExtra("image",image)

                                startActivity(intent)

                                dialog.dismiss()

                            }

                        else{


                            Toast.makeText(this@LoginActivity, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }

                    }
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                    }

                })
            }


        }
        }

        else
        {
            Toast.makeText(this, "Please connect to a network", Toast.LENGTH_SHORT).show()
        }

    }

//    override fun onResume() {
//        super.onResume()
//
//        binding.progressBAr.setVisibility(View.GONE)
//        binding.edtUsername.setText("")
//        binding.edtPassword.setText("")
//    }

    }


