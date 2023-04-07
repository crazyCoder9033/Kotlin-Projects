package com.example.gulzaarsahabkishayari

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.gulzaarsahabkishayari.databinding.ActivityQuoteDisplayBinding


class QuoteDisplayActivity : AppCompatActivity() {
    lateinit var binding:ActivityQuoteDisplayBinding
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



//        binding.imgDownload.setOnClickListener {
//         var drawable : BitmapDrawable= binding.imgDisplayImage.drawable as BitmapDrawable
//            var bitmap : Bitmap= drawable.bitmap
//
//            var filepath: File = Environment.getExternalStorageDirectory()
//            var dir  =File(filepath.absolutePath +"/Demo/")
//            dir.mkdir()
//             var file=File(dir,System.currentTimeMillis().toString()+".jpg")
//
//            OutputStream= FileOutputStream(file)
//        }

    }
}