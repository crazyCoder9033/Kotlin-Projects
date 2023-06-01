package com.example.touradvisor.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.touradvisor.Adapter.ThirdImageAdapter
import com.example.touradvisor.ModelClass.SecondImageSliderModel
import com.example.touradvisor.databinding.ActivityAllDetailsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityAllDetailsBinding
    lateinit var firebaseDatabase: DatabaseReference
    lateinit var thirdImageAdapter: ThirdImageAdapter
    var imageList = ArrayList<SecondImageSliderModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAllDetailsBinding.inflate(layoutInflater)
        firebaseDatabase = FirebaseDatabase.getInstance().getReference()

        conditions()
        cart()
        setContentView(binding.root)
    }

    private fun cart() {

        binding.btnAddToCart.setOnClickListener {
            var intent = Intent(this,CartActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun conditions() {
        if (intent.hasExtra("hotel")) {
            workingClass()
            imageSlider()
        } else if (intent.hasExtra("place")) {
            place()
            placeImageSlider()
        } else if (intent.hasExtra("activity")) {

            activity()
            activityImageSlider()
        }
    }

    fun activityImageSlider()
    {
        var key = intent.getStringExtra("key").toString()
        var value = intent.getStringExtra("value").toString()
        firebaseDatabase.child("top").child(value).child("activity").child(key).child("viewPager").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                imageList.clear()
                for (photo in snapshot.children)
                {
                    var data = photo.getValue(SecondImageSliderModel::class.java)
                    data?.let { it1 -> imageList.add(it1) }
                    data?.image=photo.child("image").value.toString()

                    Log.e("TAG", "vpp: "+data?.image.toString())
                }
                thirdImageAdapter= ThirdImageAdapter(imageList,this@AllDetailsActivity)
                binding.VPView.adapter=thirdImageAdapter
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })

        firebaseDatabase.child("top").child(value).child("activity").child(key).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var location = snapshot.child("location").value.toString()

                binding.txtLocation.setText(location)
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

    }

    fun activity()
    {
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }

        var value = intent.getStringExtra("value").toString()
        var key = intent.getStringExtra("key").toString()

        firebaseDatabase.child("top").child(value).child("activity").child(key).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var details = snapshot.child("details").value.toString()
                var title = snapshot.child("name").value.toString()
                var rating = snapshot.child("rating").value.toString()
                var price = snapshot.child("amount").value.toString()
                binding.txtDetails.setText(details)
                binding.txtTitle.setText(title)
                binding.txtRating.setText(rating)
                binding.txtPrice.setText(price)
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }


    private fun placeImageSlider() {
        var key = intent.getStringExtra("key").toString()
        var value = intent.getStringExtra("value").toString()
        firebaseDatabase.child("top").child(value).child("place").child(key).child("viewPager").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                imageList.clear()
                for (photo in snapshot.children)
                {
                    var data = photo.getValue(SecondImageSliderModel::class.java)
                    data?.let { it1 -> imageList.add(it1) }
                    data?.image=photo.child("image").value.toString()

                    Log.e("TAG", "vpp: "+data?.image.toString())
                }
                thirdImageAdapter= ThirdImageAdapter(imageList,this@AllDetailsActivity)
                binding.VPView.adapter=thirdImageAdapter
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })

        firebaseDatabase.child("top").child(value).child("place").child(key).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var location = snapshot.child("location").value.toString()

                binding.txtLocation.setText(location)
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun place() {
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }

        var value = intent.getStringExtra("value").toString()
        var key = intent.getStringExtra("key").toString()

        firebaseDatabase.child("top").child(value).child("place").child(key).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var details = snapshot.child("details").value.toString()
                var title = snapshot.child("name").value.toString()
                var rating = snapshot.child("rating").value.toString()
                var price = snapshot.child("amount").value.toString()
                binding.txtDetails.setText(details)
                binding.txtTitle.setText(title)
                binding.txtRating.setText(rating)
                binding.txtPrice.setText(price)
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

    }


    private fun imageSlider() {
        var key = intent.getStringExtra("key").toString()
        var value = intent.getStringExtra("value").toString()
        firebaseDatabase.child("top").child(value).child("hotel").child(key).child("viewPager").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                imageList.clear()
                for (photo in snapshot.children)
                {
                    var data = photo.getValue(SecondImageSliderModel::class.java)
                    data?.let { it1 -> imageList.add(it1) }
                    data?.image=photo.child("image").value.toString()
                    Log.e("TAG", "vpp: "+data?.image.toString())
                }
                thirdImageAdapter= ThirdImageAdapter(imageList,this@AllDetailsActivity)
                binding.VPView.adapter=thirdImageAdapter
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })

        firebaseDatabase.child("top").child(value).child("hotel").child(key).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               var location = snapshot.child("location").value.toString()
                binding.txtLocation.setText(location)
            }
            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
    private fun workingClass() {

        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
//
        var value = intent.getStringExtra("value").toString()
        var key = intent.getStringExtra("key").toString()

        firebaseDatabase.child("top").child(value).child("hotel").child(key).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var details = snapshot.child("details").value.toString()
                var title = snapshot.child("name").value.toString()
                var rating = snapshot.child("rating").value.toString()
                var price = snapshot.child("amount").value.toString()

                binding.txtDetails.setText(details)
                binding.txtTitle.setText(title)
                binding.txtRating.setText(rating)
                binding.txtPrice.setText(price)

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
}