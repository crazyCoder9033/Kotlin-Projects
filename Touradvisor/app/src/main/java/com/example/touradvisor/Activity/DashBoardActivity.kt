package com.example.touradvisor.Activity

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.touradvisor.Fragment.FavouriteFragment
import com.example.touradvisor.Fragment.HomeFragment
import com.example.touradvisor.Fragment.ProfileFragment
import com.example.touradvisor.Fragment.SearchFragment
import com.example.touradvisor.R
import com.example.touradvisor.databinding.ActivityDashBoardBinding
import com.google.android.material.navigation.NavigationBarView

class DashBoardActivity : AppCompatActivity() {
    lateinit var binding: ActivityDashBoardBinding
    lateinit var fragmentDisplay:FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentDisplay=findViewById(R.id.fragmentDisplay)
        loadFragment(HomeFragment())
        workingClass()
    }

    private fun workingClass() {

        Log.e("himanshu", "workingClass: " )


       binding.bottomNavigationView.setOnItemSelectedListener (object :NavigationBarView.OnItemSelectedListener{
           override fun onNavigationItemSelected(item: MenuItem): Boolean {
               lateinit var fragment: Fragment
               when (item.itemId) {
                   R.id.home_bottom -> {

                       fragment = HomeFragment()
                       loadFragment(fragment)
                   }
                   R.id.search_bottom -> {
                       fragment = SearchFragment()
                       loadFragment(fragment)

                   }
                   R.id.fav_bottom -> {
                       fragment = FavouriteFragment()
                      loadFragment(fragment)
                   }
                   R.id.profile_bottom -> {
                       fragment = ProfileFragment()
                       loadFragment(fragment)
                   }
               }


               return true
           }

       })

    }
    fun loadFragment(f : Fragment)
    {
        val fm : FragmentManager =supportFragmentManager

        val fragmentTransaction: FragmentTransaction =fm.beginTransaction()

        fragmentTransaction.replace(R.id.fragmentDisplay,f)
        fragmentTransaction.commit()
    }



}