package com.example.lobolo

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lobolo.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var textToSpeech: TextToSpeech? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var textToSpeech = TextToSpeech(applicationContext
        ) { i ->
            // if No error is found then only it will run
            if (i != TextToSpeech.ERROR) {
                // To Choose language of speech
                textToSpeech?.setLanguage(Locale.UK)
            }
        }

        // Adding OnClickListener

        binding.btnBulwao.setOnClickListener {
            textToSpeech?.speak(binding.edtSpeech.text.toString(),TextToSpeech.QUEUE_FLUSH,null)
        }
    }
}