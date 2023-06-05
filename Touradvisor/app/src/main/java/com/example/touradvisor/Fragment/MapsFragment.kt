package com.example.touradvisor.Fragment

import android.location.Address
import android.location.Geocoder
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.touradvisor.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.io.IOException

class MapsFragment : Fragment() {
    lateinit var firebaseDatabase: DatabaseReference
    private val callback = OnMapReadyCallback { googleMap ->
        firebaseDatabase = FirebaseDatabase.getInstance().getReference()
//        val sydney = LatLng(-34.0, 151.0)
//        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))


      if (arguments?.getBoolean("slider")==true)
      {
          val value = arguments?.getString("key").toString()

          firebaseDatabase.child("slider").child(value).addValueEventListener(object :
              ValueEventListener {
              override fun onDataChange(snapshot: DataSnapshot) {

                  var location = snapshot.child("location").value.toString()

                  var addressList: List<Address>? = null

                  if (location != null || location == "") {
                      var geoCoder = Geocoder(requireContext())
                      try {
                          addressList = geoCoder.getFromLocationName(location, 1)
                      } catch (e: IOException) {
                          e.printStackTrace()
                      }
                      var address = addressList!![0]

                      var myLocation = LatLng(address.latitude, address.longitude)
                      var addeddMarker =
                          googleMap.addMarker(MarkerOptions().position(myLocation).title(location))!!
                      googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 13f))
                  }
              }
              override fun onCancelled(error: DatabaseError) {
              }
          })
      }

        else if(arguments?.getBoolean("hotel")==true)
      {
          val value = arguments?.getString("value").toString()
          val key = arguments?.getString("key").toString()

          Log.e("locationnnn", "onDataChange: "+key +"s----"+value)

          firebaseDatabase.child("top").child(value).child("hotel").child(key).addValueEventListener(object :
              ValueEventListener {
              override fun onDataChange(snapshot: DataSnapshot) {

                  var location = snapshot.child("location").value.toString()



                  var addressList: List<Address>? = null

                  if (location != null || location == "") {
                      var geoCoder = Geocoder(requireContext())
                      try {
                          addressList = geoCoder.getFromLocationName(location, 1)
                      } catch (e: IOException) {
                          e.printStackTrace()
                      }
                      var address = addressList!![0]

                      var myLocation = LatLng(address.latitude, address.longitude)
                      var addeddMarker =
                          googleMap.addMarker(MarkerOptions().position(myLocation).title(location))!!
                      googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 20f))
                  }
              }
              override fun onCancelled(error: DatabaseError) {
              }
          })
      }

      else if(arguments?.getBoolean("place")==true)
      {
          val value = arguments?.getString("value").toString()
          val key = arguments?.getString("key").toString()

          Log.e("locationnnn", "onDataChange: "+key +"s----"+value)

          firebaseDatabase.child("top").child(value).child("place").child(key).addValueEventListener(object :
              ValueEventListener {
              override fun onDataChange(snapshot: DataSnapshot) {

                  var location = snapshot.child("location").value.toString()



                  var addressList: List<Address>? = null

                  if (location != null || location == "") {
                      var geoCoder = Geocoder(requireContext())
                      try {
                          addressList = geoCoder.getFromLocationName(location, 1)
                      } catch (e: IOException) {
                          e.printStackTrace()
                      }
                      var address = addressList!![0]

                      var myLocation = LatLng(address.latitude, address.longitude)
                      var addeddMarker =
                          googleMap.addMarker(MarkerOptions().position(myLocation).title(location))!!
                      googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 20f))
                  }
              }
              override fun onCancelled(error: DatabaseError) {
              }
          })
      }

      else if(arguments?.getBoolean("activity")==true)
      {
          val value = arguments?.getString("value").toString()
          val key = arguments?.getString("key").toString()

          Log.e("locationnnn", "onDataChange: "+key +"s----"+value)

          firebaseDatabase.child("top").child(value).child("activity").child(key).addValueEventListener(object :
              ValueEventListener {
              override fun onDataChange(snapshot: DataSnapshot) {

                  var location = snapshot.child("location").value.toString()



                  var addressList: List<Address>? = null

                  if (location != null || location == "") {
                      var geoCoder = Geocoder(requireContext())
                      try {
                          addressList = geoCoder.getFromLocationName(location, 1)
                      } catch (e: IOException) {
                          e.printStackTrace()
                      }
                      var address = addressList!![0]

                      var myLocation = LatLng(address.latitude, address.longitude)
                      var addeddMarker =
                          googleMap.addMarker(MarkerOptions().position(myLocation).title(location))!!
                      googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 20f))
                  }
              }
              override fun onCancelled(error: DatabaseError) {
              }
          })
      }







    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}