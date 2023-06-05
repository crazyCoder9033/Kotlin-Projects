package com.example.touradvisor.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.touradvisor.R
import com.example.touradvisor.Adapter.SliderAdapter
import com.example.touradvisor.DataClass.SliderData

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
            var intent = Intent(this@WelcomeScreenActivity, LoginScreenActivity::class.java)
            startActivity(intent)
            finish()
        }

        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo= connectivityManager.activeNetworkInfo
        if (networkInfo !=null && networkInfo.isConnected) {

            binding.idBtnSkip.setOnClickListener {
                var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
                myEdit.putBoolean("skipIntro", true)
                myEdit.commit()
                var intent = Intent(this, LoginScreenActivity::class.java)
                startActivity(intent)
                finish()
            }
            sliderList = ArrayList()
            sliderList.add(
                SliderData(
                    "Browse Locations",
                    R.drawable.screenone
                )
            )
            sliderList.add(SliderData("Find Best Hotels near by you", R.drawable.screentwo))
            sliderList.add(
                SliderData(
                    "Plan your trip",
                    R.drawable.screenthree
                )
            )


            var viewListener: ViewPager.OnPageChangeListener =
                object : ViewPager.OnPageChangeListener {
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
        else{
            Toast.makeText(this, "Please connect to a network", Toast.LENGTH_SHORT).show()

        }

    }
}