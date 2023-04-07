package com.example.viewphew

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {

    lateinit var VPView: ViewPager
    lateinit var txtCount: TextView

    var imagelist = arrayOf(
        R.drawable.h,
        R.drawable.i,
        R.drawable.m,
        R.drawable.a,
        R.drawable.n,
        R.drawable.s,
        R.drawable.h,
        R.drawable.u
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intview()
    }

    private fun intview() {
        VPView = findViewById(R.id.VPView)
        txtCount = findViewById(R.id.txtCount)
        VPView.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.e("TAG", "onPageScrolled: " )
            }

            override fun onPageSelected(position: Int) {
                Log.e("TAG", "onPageScrolled:-- " )
                txtCount.text= "${position+1}/8"
            }

            override fun onPageScrollStateChanged(state: Int) {
                Log.e("TAG", "onPageScrolled: -->" )
            }

        })

        var imageAdapter = ImageAdapter(imagelist)

        VPView.adapter = imageAdapter


    }
}