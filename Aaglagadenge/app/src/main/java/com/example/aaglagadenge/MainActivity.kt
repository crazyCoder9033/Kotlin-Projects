package com.example.aaglagadenge

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.aaglagadenge.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    lateinit var firebaseDatabase: FirebaseDatabase
    var PICK_IMAGE_REQUEST=100
    lateinit var filePath:Uri
    lateinit var storageReference :StorageReference
    lateinit var bitmap : Bitmap
    lateinit var images : String
    var studentList = ArrayList<StudentModelClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        workingClass()
    }

    private fun workingClass() {


        firebaseDatabase = FirebaseDatabase.getInstance()
        storageReference=FirebaseStorage.getInstance().reference


        binding.btnRegister.setOnClickListener {
            var name = binding.edtName.text.toString()
            var address = binding.edtAddress.text.toString()
            var phone = binding.edtPhone.text.toString()
            var email = binding.edtEmail.text.toString()


            if (name.isEmpty())
            {
                Toast.makeText(this, "please enter name", Toast.LENGTH_SHORT).show()
            }

            else if(address.isEmpty())
            {
                Toast.makeText(this, "please enter address", Toast.LENGTH_SHORT).show()
            }
            else if (phone.isEmpty())
            {
                Toast.makeText(this, "please enter your phone number", Toast.LENGTH_SHORT).show()
            }
            else if (email.isEmpty())
            {
                Toast.makeText(this, "please enter email", Toast.LENGTH_SHORT).show()
            }

            else
            {

                val key = firebaseDatabase.reference.child("StudentTb").push().key ?: ""
                val obj = StudentModelClass(key, name, address, phone, email, images)
                firebaseDatabase.reference.child("StudentTb").child(key).setValue(obj)
                    .addOnCompleteListener {

                        if (it.isSuccessful) {
                            binding.edtName.text.clear()
                            binding.edtAddress.text.clear()
                            binding.edtPhone.text.clear()
                            binding.edtEmail.text.clear()
                            binding.edtPassword.text.clear()

//                            binding.imgView.setImageURI(imageLink)
                            Toast.makeText(this, "Record Added Successfully ", Toast.LENGTH_SHORT)
                                .show()

                        }
                    }.addOnFailureListener {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
            }

        }

        binding.btnDisplay.setOnClickListener {
            var intent = Intent(this, DisplayDetailsActivity::class.java)
            startActivity(intent)
        }


        binding.btnSelectImage.setOnClickListener {
            selectImageFromGallery()


        }

        binding.btnUpload.setOnClickListener {
                uploadImagetoFirebase()
        }

    }

    private fun uploadImagetoFirebase() {

        if (filePath != null) {

            // Code for showing progressDialog while uploading
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.show()

            // Defining the child of storageReference
            val ref: StorageReference = storageReference.child("images/" + UUID.randomUUID().toString())

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                .addOnCompleteListener{
//                it.result.uploadSessionUri

                    ref.downloadUrl.addOnSuccessListener {

                        images=it.toString()

                    }
                }
                .addOnSuccessListener { // Image uploaded successfully
                    // Dismiss dialog

                    progressDialog.dismiss()



                    Toast.makeText(this@MainActivity, "Image Uploaded!!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e -> // Error, Image not uploaded
                    progressDialog.dismiss()
                    Toast.makeText(this@MainActivity, "Failed " + e.message, Toast.LENGTH_SHORT).show()
                }
                .addOnProgressListener { taskSnapshot ->
                    // Progress Listener for loading
                    // percentage on the dialog box
                    val progress = (100.0
                            * taskSnapshot.bytesTransferred
                            / taskSnapshot.totalByteCount)
                    progressDialog.setMessage(
                        "Uploaded " + progress.toInt() + "%")
                }
        }
    }

    private fun selectImageFromGallery() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(
                intent,
                "Select Image from here..."),
            PICK_IMAGE_REQUEST)


        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (requestCode === PICK_IMAGE_REQUEST && resultCode === RESULT_OK && data != null && data.getData() != null) {

                // Get the Uri of data
                filePath = data.getData()!!
                try {

                    // Setting image on image view using Bitmap
                     bitmap = MediaStore.Images.Media
                        .getBitmap(
                            contentResolver,
                            filePath)
                    binding.imgView.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    // Log the exception
                    e.printStackTrace()
                }
            }
        }
    }
    }


