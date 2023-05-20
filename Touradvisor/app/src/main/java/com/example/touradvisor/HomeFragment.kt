package com.example.touradvisor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.touradvisor.databinding.FragmentHomeBinding
import com.google.firebase.database.*

class HomeFragment : Fragment() {
lateinit var imageAdapter: ImageAdapter
    lateinit var homeBinding: FragmentHomeBinding
    lateinit var firebaseDatabase: DatabaseReference
    var imageList = ArrayList<ImageSliderModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        homeBinding=FragmentHomeBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return homeBinding.root


        imageSlider()

    }

    private fun imageSlider() {


        homeBinding.loutCity.setOnClickListener {
            Log.e("hhhh", "imageSlider: " )
        }

        firebaseDatabase = FirebaseDatabase.getInstance().getReference()
        firebaseDatabase.child("slider").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                imageList.clear()

                for (photo in snapshot.children)
                {
                    var data = photo.getValue(ImageSliderModel::class.java)
                    data?.let { it1 -> imageList.add(it1) }
                    data?.image=photo.child("slider").value.toString()
                    imageList.add(data!!)

                }
                imageAdapter.notifyDataSetChanged()

            }
            override fun onCancelled(error: DatabaseError) {

            }

        })
        imageAdapter=ImageAdapter(imageList,this@HomeFragment)
        homeBinding.VPView.adapter=imageAdapter


    }

}