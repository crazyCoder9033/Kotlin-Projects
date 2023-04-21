package com.example.myregistrationfrom.Activity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myregistrationfrom.databinding.ActivityDisplayUserDetailsBinding


class DisplayUserDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDisplayUserDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDisplayUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        working()
    }

    private fun working() {

        var name: String? = intent.getStringExtra("fullName")
        var age: String? = intent.getStringExtra("age")
        var address: String? = intent.getStringExtra("address")
        var mobile: String? = intent.getStringExtra("mobile")
        var gender: String? = intent.getStringExtra("gender")
        var hobbies: String? = intent.getStringExtra("hobbies")

//        val extras = intent.extras
//        val byteArray = extras?.getByteArray("picture")
//        val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.)
//
//        binding.imgView.setImageBitmap(bmp)

        val bitmap = intent.getParcelableExtra("BitmapImage") as Bitmap?

        binding.imgView.setImageBitmap(bitmap)


        binding.txtNameDisplay.text=name
        binding.txtAgeDisplay.text=age
        binding.txtAddressDisplay.text=address
        binding.txtMobileDisplay.text=mobile
        binding.txtGenderDisplay.text=gender
        binding.txtHobbies.text=hobbies

        binding.txtDisplayBack.setOnClickListener {
            onBackPressed()
        }

        binding.txtDone.setOnClickListener {
            val intent = Intent(this@DisplayUserDetailsActivity,MainActivity::class.java)
            startActivity(intent)
        }



    }
}