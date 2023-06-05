package com.example.touradvisor.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.touradvisor.Adapter.SecondImageAdapter
import com.example.touradvisor.Fragment.MapsFragment
import com.example.touradvisor.ModelClass.SecondImageSliderModel
import com.example.touradvisor.R
import com.example.touradvisor.databinding.ActivityDisplayBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DisplayActivity : AppCompatActivity() {
    lateinit var binding:ActivityDisplayBinding
    var imageList = ArrayList<SecondImageSliderModel>()
     lateinit var getTitle : String
    lateinit var secondImageAdapter: SecondImageAdapter
    lateinit var firebaseDatabase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDisplayBinding.inflate(layoutInflater)
        firebaseDatabase = FirebaseDatabase.getInstance().getReference()
        workingClass()
        imageSlider()
        mapShow()

        setContentView(binding.root)
    }

    private fun mapShow() {

//        val args = Bundle()
//        args.putString("key",key)
//        supportFragmentManager.beginTransaction().replace(R.id.mapView, MapsFragment()).commit()
    }

    private fun imageSlider() {
        var key = intent.getStringExtra("key").toString()
        Log.e("TAG", "imageSlider: "+key )
        firebaseDatabase.child("slider").child(key).child("viewPager").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                imageList.clear()
                for (photo in snapshot.children)
                {
                    var data = photo.getValue(SecondImageSliderModel::class.java)
                        data?.let { it1 -> imageList.add(it1) }
                    data?.image=photo.child("image").value.toString()

                    Log.e("TAG", "vpp: "+data?.image.toString())
                }
                secondImageAdapter= SecondImageAdapter(imageList, this@DisplayActivity)
                binding.VPView.adapter=secondImageAdapter
                binding.wormDotsIndicator.attachTo(binding.VPView)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        val fragment = MapsFragment()

        val args = Bundle()
        args.putString("key",key)
        args.putBoolean("slider",true)
        fragment.setArguments(args).toString()
        supportFragmentManager.beginTransaction().replace(R.id.mapView, fragment).commit()


    }


    private fun workingClass() {

        var key = intent.getStringExtra("key").toString()
            firebaseDatabase.child("slider").child(key).addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                   getTitle= snapshot.child("name").value.toString()
                    var thumbnail= snapshot.child("image").value.toString()
                    var location = snapshot.child("location").value.toString()
                    var open = snapshot.child("open").value.toString()
                    var details = snapshot.child("details").value.toString()
                    var spent = snapshot.child("spent").value.toString()
//                    Log.e("mila?", "onDataChange: "+getTitle+"--"+snapshot.child("name").value.toString() )
                    binding.txtTitle.setText(getTitle)
                    binding.txtLocation.setText(location)
                    binding.txtOpenHours.setText(open)
                    binding.txtDetails.setText(details)
                    binding.txtTimeSpent.setText(spent)
//                    Glide.with(this@DisplayActivity).load(thumbnail).into(binding.imgView)
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

        binding.imgBack.setOnClickListener {
            onBackPressed()
        }

    }
}