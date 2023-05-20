package com.example.customfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    lateinit var fragDisplay:FrameLayout
    lateinit var loutHomeButton:LinearLayout
    lateinit var loutSearch:LinearLayout
    lateinit var loutLikedWorkout:LinearLayout
    lateinit var loutYourLibrary:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intview()
    }

    private fun intview() {
        fragDisplay=findViewById(R.id.fragDisplay)
        loutHomeButton=findViewById(R.id.loutHomeButton)
        loutSearch=findViewById(R.id.loutSearch)
        loutLikedWorkout=findViewById(R.id.loutLikedWorkout)
        loutYourLibrary=findViewById(R.id.loutYourLibrary)


        var hm=HomeFragment()
        loadFragment(hm)

        loutHomeButton.setOnClickListener {
            loadFragment(HomeFragment())
        }

        loutSearch.setOnClickListener {
            loadFragment(SearchFragment())
        }

        loutLikedWorkout.setOnClickListener {
            loadFragment(LikedFragment())
        }

        loutYourLibrary.setOnClickListener {
            loadFragment(LibraryFragment())
        }

    }

    fun loadFragment(f : Fragment)
    {
        val fm : FragmentManager=supportFragmentManager
        val fragmentTransaction: FragmentTransaction=fm.beginTransaction()
        fragmentTransaction.replace(R.id.fragDisplay,f)
        fragmentTransaction.commit()
    }
}