package com.example.myregistrationfrom.Activity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.myregistrationfrom.DataBase
import com.example.myregistrationfrom.databinding.ActivityAddFormBinding


class AddFormActivity : AppCompatActivity() {
    lateinit var binding:ActivityAddFormBinding
    var hobbies = StringBuilder()
    lateinit var db : DataBase
    var id_number=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db= DataBase(this)
        addImage()
    }

    private fun addImage() {

        if (intent != null && intent.hasExtra("update")){

            var newName: String? = intent.getStringExtra("fullName")
            var newAge: String? = intent.getStringExtra("age")
            var newGender: String? = intent.getStringExtra("gender")
            var newMobile: String? = intent.getStringExtra("mobile")
            var newAddress: String? = intent.getStringExtra("address")
            var icon: String? = intent.getStringExtra("key_icon")
            id_number= intent.getIntExtra("id",0)
            Log.e("TAG", "ddd: "+id_number)


            binding.btnSubmit.setText(icon)
            binding.edtName.setText(newName)
            binding.edtAge.setText(newAge)
            binding.edtMobile.setText(newMobile)
            binding.edtAddress.setText(newAddress)


        }



        val galleryLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                // There are no request codes
                val data = result.data
                val uri = data!!.data
               binding.imgAddImage.setImageURI(uri)
            }
        }
        binding.imgAddImage.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
//             startActivityForResult(gallery, PICK_IMAGE);
            galleryLauncher.launch(gallery)
        }


        binding.txtBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnSubmit.setOnClickListener {
            var fullName=binding.edtName.text.toString()
            var age=binding.edtAge.text.toString()
            var address=binding.edtAddress.text.toString()
            var mobile=binding.edtMobile.text.toString()


            val selectId: Int = binding.rdgGender.checkedRadioButtonId
            var selectedRadioButton: RadioButton = findViewById(selectId)
            var gender = selectedRadioButton.text.toString()

            Log.e("TAG", "gg: "+gender)


//            val bmp = BitmapFactory.decodeResource(resources,binding.imgAddImage.imageAlpha)
//            val stream = ByteArrayOutputStream()
//            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream)
//            val byteArray: ByteArray = stream.toByteArray()

            binding.imgAddImage.buildDrawingCache()
            val bitmap: Bitmap = binding.imgAddImage.getDrawingCache()




//      Languages CHECKBOX
            if (binding.chkMovies.isChecked()) {
                hobbies.append(" " + binding.chkMovies.getText().toString())
            }
            if (binding.chkSports.isChecked()) {
                hobbies.append(" " + binding.chkSports.getText().toString())
            }
            if (binding.chkCooking.isChecked()) {
                hobbies.append(" " + binding.chkCooking.getText().toString())
            }




//            conditions
            if (fullName.isEmpty()) {
                Toast.makeText(this, "Please Enter your name", Toast.LENGTH_SHORT).show()
            }
        else if(fullName.length<5 || fullName.length>10){
            Toast.makeText(this, "Full Name should more than 5 letters", Toast.LENGTH_SHORT).show();
        }

            else if (age.isEmpty()) {
                Toast.makeText(this, "Please enter Age", Toast.LENGTH_SHORT).show()
            }

            else if (mobile.isEmpty()) {
                Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT).show()
            } else if (mobile.length < 10) {
                Toast.makeText(this, "Phone number should be 10 digits", Toast.LENGTH_SHORT).show()
            }
            else if (address.isEmpty()) {
                Toast.makeText(this, "Please Address", Toast.LENGTH_SHORT).show()
            }
            else if (hobbies == null) {
                Toast.makeText(this, "Please select at least one Hobbie", Toast.LENGTH_SHORT).show()
            }
//            Log.e("All", "addImage: "+fullName+"-"+age+""+address+""+mobile+""+gender+""+hobbies)

            else
            {
                val intent = Intent(this@AddFormActivity,DisplayUserDetailsActivity::class.java)
                intent.putExtra("fullName",fullName)
                intent.putExtra("age",age)
                intent.putExtra("address",address)
                intent.putExtra("mobile",mobile)
                intent.putExtra("gender",gender)
                intent.putExtra("hobbies",""+hobbies.toString())
                intent.putExtra("BitmapImage", bitmap);
                startActivity(intent)

                Log.e("fff", "addImage: "+hobbies)

                if (binding.btnSubmit.text.toString().equals("update"))
                {
                    db.updateRecord(fullName,age,gender,address,mobile,id_number)
                    Log.e("TAG", "aaaa: " +id_number)
                }
                else {

                    db.insertRecord(fullName, age, gender, address, mobile)
                }
            }

        }




    }


}