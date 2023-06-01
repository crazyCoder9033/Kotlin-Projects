package com.example.touradvisor.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.touradvisor.R
import com.example.touradvisor.databinding.FragmentFavouriteBinding


class FavouriteFragment : Fragment() {
    lateinit var favouriteBinding: FragmentFavouriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       favouriteBinding= FragmentFavouriteBinding.inflate(layoutInflater,container,false)

        workingClass()
        return favouriteBinding.root
    }

    private fun workingClass() {


    }

}