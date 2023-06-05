package com.example.touradvisor.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.touradvisor.R
import com.example.touradvisor.databinding.FragmentSuratBinding


class SuratFragment : Fragment() {
lateinit var suratFragment : FragmentSuratBinding
 var position=0


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

        val newGamefragment = CityHotelFragment()
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

        val newGamefragment = CityPlaceFragment()
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
        val newGamefragment = CityActivityFragment()
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