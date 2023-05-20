package com.example.videoplayer

import android.R
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.videoplayer.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var videoLinkList=ArrayList<String>()
    lateinit var videoView :VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        workingClass()
    }

    private fun workingClass() {

       videoView= binding.vp


        val videoUrl="http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
        val uri: Uri = Uri.parse(videoUrl)

        videoView.setVideoURI(uri);

       var mp = MediaController(this)
        mp.setAnchorView(videoView)

        mp.setMediaPlayer(videoView)
        videoView.setMediaController(mp)
        videoView.start()



    }
}