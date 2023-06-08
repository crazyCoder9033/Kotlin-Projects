package com.example.validationform

import android.R
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.VibrationEffect.DEFAULT_AMPLITUDE
import android.os.Vibrator
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.validationform.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var binding: ActivityMainBinding
   lateinit var count : String

    var country = arrayOf<String?>("India", "Russia", "UAE", "USA", "Korea", "Other")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        workingClass()



    }

    private fun vibrate() {
        var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator!!.vibrate(
                VibrationEffect.createOneShot(500, VibrationEffect.EFFECT_HEAVY_CLICK))
        } else {
            //deprecated in API 26
            vibrator!!.vibrate(500)
        }
    }

    private fun workingClass() {

        binding.spinner.setOnItemSelectedListener(this);
        val aa: ArrayAdapter<*> = ArrayAdapter<Any?>(this, R.layout.simple_spinner_item, country)
        aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinner.setAdapter(aa)

binding.btnSubmit.setOnClickListener {
    var name = binding.edtName.text.toString()
    var age = binding.edtAge.text.toString()
    var email = binding.edtEmail.text.toString()
    var address = binding.edtAddress.text.toString()
    var mobile = binding.edtMobile.text.toString()
    var password=binding.edtPassword.text.toString()
    var gender=binding.rdgGender.checkedRadioButtonId




    if (name.isEmpty())
    {
        Toast.makeText(this, "name field cannot be empty", Toast.LENGTH_SHORT).show()
        vibrate()
    }

    else if (name.length< 3 )
    {
        Toast.makeText(this, "characters should be at least 3 letters", Toast.LENGTH_SHORT).show()
        vibrate()
    }


    else if (age.isEmpty())
    {
        Toast.makeText(this, "age cannot be empty", Toast.LENGTH_SHORT).show()
        vibrate()
    }

        else if (gender.equals(-1))
    {
        Toast.makeText(this, "please select a gender ", Toast.LENGTH_SHORT).show()
        vibrate()
    }


        else if (email.isEmpty())
    {
        Toast.makeText(this, "please enter email", Toast.LENGTH_SHORT).show()
        vibrate()
    }
        else if ( !Patterns.EMAIL_ADDRESS.matcher(email).matches())
    {
        Toast.makeText(this, "please enter proper email", Toast.LENGTH_SHORT).show()
        vibrate()
    }

    else if (password.isEmpty()) {
        Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show()
        vibrate()
    } else if (password.length < 8) {
        Toast.makeText(this, "Minimum 8 Character Password", Toast.LENGTH_SHORT).show()
        vibrate()
    } else if (!password.matches(".*[A-Z].*".toRegex())) {

        Toast.makeText(this, "Must Contain 1 Upper-case Character", Toast.LENGTH_SHORT).show()
        vibrate()
    } else if (!password.matches(".*[a-z].*".toRegex())) {
        Toast.makeText(this, "Must Contain 1 Lower-case Character", Toast.LENGTH_SHORT).show()
        vibrate()
    } else if (!password.matches(".*[@#\$%^&+=].*".toRegex())) {
        Toast.makeText(this, "Must Contain 1 Special Character (@#\$%^&+=)", Toast.LENGTH_SHORT).show()
        vibrate()
    }

    else if (address.isEmpty())
    {
        Toast.makeText(this, "please enter proper address", Toast.LENGTH_SHORT).show()
        vibrate()
    }

    else if (mobile.isEmpty())
    {
        Toast.makeText(this, "mobile number field cannot be empty", Toast.LENGTH_SHORT).show()
        vibrate()
    }

    else if (mobile.length<=9)
    {
        Toast.makeText(this, "please enter valid phone number", Toast.LENGTH_SHORT).show()
        vibrate()
    }

    else if(!binding.chkMovies.isChecked && !binding.chkSports.isChecked && !binding.chkCooking.isChecked)
    {
        Toast.makeText(this, "please select at least one hobby", Toast.LENGTH_SHORT).show()
        vibrate()
    }
    
    else if (count.isEmpty())
    {
        Toast.makeText(this, "please a country", Toast.LENGTH_SHORT).show()
        vibrate()
    }



    else
    {

        Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show()
    }



}
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        Toast.makeText(getApplicationContext(),country[position] , Toast.LENGTH_LONG).show();
             count= country[position].toString()

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

}