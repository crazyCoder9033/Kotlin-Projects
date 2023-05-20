package com.example.retrofitapicalling

import android.Manifest.permission.*
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresPermission.Read
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.retrofitapicalling.databinding.ActivityDisplayLoginUserDetailsBinding
import com.example.retrofitapicalling.login.LoginActivity

class DisplayLoginUserDetails : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var user :String
    lateinit var pass :String
    lateinit var binding: ActivityDisplayLoginUserDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDisplayLoginUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences=getSharedPreferences("MySharedPref", MODE_PRIVATE)
        logout()
        workingClass()
        checkPermissions()

    }

    private fun checkPermissions() {
        binding.btnCamera.setOnClickListener {
         if (checkPermission())
         {
             Toast.makeText(this, "Permission has been granted already", Toast.LENGTH_SHORT).show()
         }
            else
         {
             requestPermission()
         }

        }

        binding.btnGallery.setOnClickListener {
            if (checkPermission())
            {
                Toast.makeText(this, "Permission has been granted already", Toast.LENGTH_SHORT).show()
            }
            else
            {
                requestPermission()
            }
        }

    }

    private fun checkPermission(): Boolean
    {
       val readable_storage=ContextCompat.checkSelfPermission(applicationContext,READ_EXTERNAL_STORAGE)
       val writeable_storage=ContextCompat.checkSelfPermission(applicationContext,WRITE_EXTERNAL_STORAGE)
        val camera = ContextCompat.checkSelfPermission(applicationContext,CAMERA)
        return readable_storage == PackageManager.PERMISSION_GRANTED && writeable_storage== PackageManager.PERMISSION_GRANTED && camera== PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission()
    {
        ActivityCompat.requestPermissions(this, arrayOf(WRITE_EXTERNAL_STORAGE,
            READ_EXTERNAL_STORAGE, CAMERA),100)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode)
        {
            100 -> if (grantResults.size>0)
            {
                val write_Storage = grantResults[0]== PackageManager.PERMISSION_GRANTED
                val read_Storage = grantResults[1]== PackageManager.PERMISSION_GRANTED
                val camera = grantResults[2]== PackageManager.PERMISSION_GRANTED

                if (write_Storage && read_Storage && camera)
                {
                    Toast.makeText(this, "Permission granted, Now you can access storage and camera", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "You don't have permission to proceed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun logout() {

        binding.btnLogout.setOnClickListener {
            var myEdit : SharedPreferences.Editor=sharedPreferences.edit()
            myEdit.remove("isLogin")
            myEdit.commit()
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun workingClass() {

//        var fName: String? =intent.getStringExtra("fName")
//        var id: String? =intent.getStringExtra("id")
//        var email: String? =intent.getStringExtra("email")
//        var gender: String? =intent.getStringExtra("gender")
//        var lName: String? =intent.getStringExtra("lName")
//        var username: String? =intent.getStringExtra("username")
////        var image: String? =intent.getStringExtra("image")
//


//        binding.txtfName.setText(fName)
//        binding.txtID.setText(id)
//        binding.txtEmail.setText(email)
//        binding.txtGender.setText(gender)
//        binding.txtlName.setText(lName)
//        binding.txtUsername.setText(username)


        var image = sharedPreferences.getString("image","")
        Glide.with(this ).load("${image}").into(binding.imgUser)
        binding.txtfName.text=sharedPreferences.getString("fName","")
        binding.txtlName.text=sharedPreferences.getString("lName","")
        binding.txtEmail.text=sharedPreferences.getString("email","")
        binding.txtGender.text=sharedPreferences.getString("gender","")
        binding.txtUsername.text=sharedPreferences.getString("username","")








    }


}