package com.example.touradvisor

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager

import com.example.touradvisor.databinding.ActivityWelcomeScreenBinding

class WelcomeScreenActivity : AppCompatActivity() {
    lateinit var sliderAdapter: SliderAdapter
    lateinit var sliderList: ArrayList<SliderData>
    lateinit var sharedPreferences: SharedPreferences

    lateinit var binding: ActivityWelcomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences=getSharedPreferences("MySharedPref", MODE_PRIVATE)

        introSlider()
    }

    private fun introSlider() {

        if (sharedPreferences.getBoolean("isLogin",false)== true)
        {
            var intent = Intent(this@WelcomeScreenActivity,LoginScreenActivity::class.java)
            startActivity(intent)
        }

        binding.idBtnSkip.setOnClickListener {
            var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
            myEdit.putBoolean("skipIntro", true)
            myEdit.commit()
            var intent = Intent(this,LoginScreenActivity::class.java)
            startActivity(intent)
        }
        sliderList = ArrayList()
        sliderList.add(SliderData("Browse Locations All Around the World", R.drawable.screenone))
        sliderList.add(SliderData("Find Best Hotels near by you", R.drawable.screentwo))
        sliderList.add(SliderData("Plan your trip from any part of the world",R.drawable.screenthree))


         var viewListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    binding.idTVSlideTwo.setTextColor(resources.getColor(R.color.grey))
                   binding.idTVSlideThree.setTextColor(resources.getColor(R.color.grey))
                    binding.idTVSlideOne.setTextColor(resources.getColor(R.color.white))

                } else if (position == 1) {
                    binding.idTVSlideTwo.setTextColor(resources.getColor(R.color.white))
                    binding.idTVSlideThree.setTextColor(resources.getColor(R.color.grey))
                    binding.idTVSlideOne.setTextColor(resources.getColor(R.color.grey))
                } else {
                    binding.idTVSlideTwo.setTextColor(resources.getColor(R.color.grey))
                    binding.idTVSlideThree.setTextColor(resources.getColor(R.color.white))
                    binding.idTVSlideOne.setTextColor(resources.getColor(R.color.grey))
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        }
        sliderAdapter = SliderAdapter(this, sliderList)
        binding.idViewPager.adapter = sliderAdapter
        binding.idViewPager.addOnPageChangeListener(viewListener)

    }
}