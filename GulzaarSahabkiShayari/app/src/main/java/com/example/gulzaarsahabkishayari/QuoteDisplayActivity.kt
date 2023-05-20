package com.example.gulzaarsahabkishayari

import android.R.attr.bitmap
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.gulzaarsahabkishayari.databinding.ActivityQuoteDisplayBinding
import java.io.File
import java.io.FileOutputStream
import java.util.*


class QuoteDisplayActivity : AppCompatActivity() {
    lateinit var binding:ActivityQuoteDisplayBinding
     var finalBitmap :Bitmap?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQuoteDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuote()

    }

    private fun displayQuote() {
        var shayari: String? = intent.getStringExtra("shayari")
        binding.txtShayari.text=shayari



//        binding.imgDisplayBack.setOnClickListener {
//            val intent = Intent(this@QuoteDisplayActivity, DisplayShayariActivity::class.java)
//            startActivity(intent)
//        }


        val galleryLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                // There are no request codes
                val data = result.data
                val uri = data!!.data
                binding.imgDisplayImage.setImageURI(uri)
            }
        }
        binding.imgAddImage.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
//             startActivityForResult(gallery, PICK_IMAGE);
            galleryLauncher.launch(gallery)
        }


        binding.imgShare.setOnClickListener {

            val a = Intent(Intent.ACTION_SEND)
            a.type = "text/plain"
            a.putExtra(Intent.EXTRA_SUBJECT, "Share")
            a.putExtra(Intent.EXTRA_TEXT, ""+shayari)
            startActivity(Intent.createChooser(a, "Share"))
        }


        binding.imgCopy.setOnClickListener {

        }

        binding.imgDownload.setOnClickListener {

           val pic : View = binding.layoutDownload
           pic.isDrawingCacheEnabled=true
            val height : Int=pic.height
            val width : Int=pic.width
            pic.layout(0,0,width,height)
            pic.buildDrawingCache(true)
            val bm : Bitmap= Bitmap.createBitmap(pic.getDrawingCache())
            pic.isDrawingCacheEnabled=false
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show()
            MediaStore.Images.Media.insertImage(contentResolver,bm,null,null)

        }


    }



}