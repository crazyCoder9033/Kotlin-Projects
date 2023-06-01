package com.example.touradvisor.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.touradvisor.R
import com.example.touradvisor.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
lateinit var profileBinding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        profileBinding= FragmentProfileBinding.inflate(layoutInflater)

        workingClass()
        return profileBinding.root
    }

    private fun workingClass() {


    }

}