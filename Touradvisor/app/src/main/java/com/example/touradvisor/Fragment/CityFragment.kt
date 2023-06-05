package com.example.touradvisor.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.touradvisor.R
import com.example.touradvisor.databinding.FragmentCityBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class CityFragment : Fragment() {
lateinit var cityBinding: FragmentCityBinding
    lateinit var firebaseDatabase: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        cityBinding= FragmentCityBinding.inflate(layoutInflater,container,false)
        firebaseDatabase = FirebaseDatabase.getInstance().getReference()
       workingClass()

        return cityBinding.root
    }

    private fun workingClass() {
        val value = arguments?.getString("key").toString()

        Log.e("TAG", "workingClass: "+value )

        cityBinding.txtHotels.setOnClickListener {
            hotelFragment()
        }

    }

    private fun hotelFragment() {
        val newGamefragment = CityHotelFragment()
        val fragmentTransaction = requireFragmentManager().beginTransaction()
        fragmentTransaction.replace(R.id.cityFrameLayout, newGamefragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }



}