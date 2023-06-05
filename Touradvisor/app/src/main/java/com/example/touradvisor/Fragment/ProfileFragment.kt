package com.example.touradvisor.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.touradvisor.Activity.DashBoardActivity
import com.example.touradvisor.Activity.LoginScreenActivity
import com.example.touradvisor.R
import com.example.touradvisor.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {
lateinit var profileBinding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        profileBinding= FragmentProfileBinding.inflate(layoutInflater)
        auth = Firebase.auth
        workingClass()
        return profileBinding.root
    }

    private fun workingClass() {

profileBinding.btnLogOut.setOnClickListener {
    auth.signOut()
    var intent = Intent(context, LoginScreenActivity::class.java)
    startActivity(intent)


}
    }

}