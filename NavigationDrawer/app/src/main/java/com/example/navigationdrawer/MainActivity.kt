package com.example.navigationdrawer

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.navigationdrawer.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         working()

    }

    private fun working() {

        binding.imgMenu.setOnClickListener {
            binding.AppDrawer.openDrawer(GravityCompat.START)
        }

        binding.btnClick.setOnClickListener {




        }

    }

    fun addImageToGallery(filePath: String?, context: Context) {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        values.put(MediaStore.MediaColumns.DATA, filePath)
        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
    }




}