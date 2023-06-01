package com.example.touradvisor.Fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.touradvisor.Adapter.SuratHotelAdapter
import com.example.touradvisor.ModelClass.SuratModelClass
import com.example.touradvisor.ModelClass.TopDestinationModelClass
import com.example.touradvisor.R
import com.example.touradvisor.databinding.FragmentSuratBinding
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener


class SuratFragment : Fragment() {
lateinit var suratFragment : FragmentSuratBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       suratFragment=FragmentSuratBinding.inflate(layoutInflater,container,false)
        hotelFragment()
        workingClass()
        val location = arguments?.getString("location").toString()

        suratFragment.txtTitle.setText(location)



        return suratFragment.root
    }

    private fun hotelFragment() {

        val value = arguments?.getString("key").toString()


        val args = Bundle()
        args.putString("key",value)

        val newGamefragment = SuratHotelFragment()
        newGamefragment.setArguments(args).toString()


        val fragmentTransaction = requireFragmentManager().beginTransaction()
        fragmentTransaction.replace(R.id.suratFrameLayout, newGamefragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun placeFragment() {
        val value = arguments?.getString("key").toString()

        val args = Bundle()
        args.putString("key",value)

        val newGamefragment = SuratPlaceFragment()
        newGamefragment.setArguments(args).toString()
        val fragmentTransaction = requireFragmentManager().beginTransaction()
        fragmentTransaction.replace(R.id.suratFrameLayout, newGamefragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun activityFragment() {
        val value = arguments?.getString("key").toString()

        val args = Bundle()
        args.putString("key",value)
        val newGamefragment = SuratActivityFragment()
        newGamefragment.setArguments(args).toString()
        val fragmentTransaction = requireFragmentManager().beginTransaction()
        fragmentTransaction.replace(R.id.suratFrameLayout, newGamefragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


    private fun workingClass() {

        suratFragment.txtHotels.setOnClickListener {

            hotelFragment()
        }

        suratFragment.txtPlaces.setOnClickListener {
            placeFragment()
        }

        suratFragment.txtActivities.setOnClickListener {
            activityFragment()
        }

        suratFragment.imgBack.setOnClickListener {
            val newGamefragment = HomeFragment()
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.fragmentDisplay, newGamefragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

    }



}