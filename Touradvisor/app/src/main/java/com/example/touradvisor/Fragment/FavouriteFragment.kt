package com.example.touradvisor.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.touradvisor.R
import com.example.touradvisor.databinding.FragmentFavouriteBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener


class FavouriteFragment : Fragment() {
    lateinit var favouriteBinding: FragmentFavouriteBinding
    lateinit var firebaseDatabase: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       favouriteBinding= FragmentFavouriteBinding.inflate(layoutInflater,container,false)

        workingClass()
        return favouriteBinding.root
    }

    private fun workingClass() {
        val value = arguments?.getString("value").toString()
        val key = arguments?.getString("key").toString()

        firebaseDatabase.child("top").child(value).child("hotel").child(key).child("fav").child("1").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               var name = snapshot.child("name").value.toString()

                favouriteBinding.txt.setText(name)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }

}