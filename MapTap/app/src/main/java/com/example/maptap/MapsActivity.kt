package com.example.maptap

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.maptap.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    var latitude: Double = 0.0
    var longitude: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        binding.btnSearch.setOnClickListener {

//            latitude = binding.edtLatitude.text.toString().toDouble()
//            longitude = binding.edtLongitude.text.toString().toDouble()

            var address1= binding.edtLocation.text.toString()

            GeoCoderHandler()

            val addressList: List<Address>? = null
            GeoCodeLocation()
            val locationAddress = GeoCodeLocation()
            locationAddress.getAddressFromLocation(address1, applicationContext, GeoCoderHandler())

            val geocoder = Geocoder(this, Locale.getDefault())
            val addresses: List<Address>? = geocoder.getFromLocationName(address1, 1)
            val address: String = addresses!![0].getAddressLine(0)
            val addressNew = addressList!![0]
            val latLng = LatLng(addressNew.getLatitude(), addressNew.getLongitude())

//            val sydney = LatLng(latitude, longitude)

            mMap.addMarker(MarkerOptions().position(latLng).title(address))
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
            val zoomLevel = 14.0f
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))
        }
    }

    private class GeoCoderHandler : Handler() {
        override fun handleMessage(message: Message) {
            val locationAddress: String?
            locationAddress = when (message.what) {
                1 -> {
                    val bundle: Bundle = message.getData()
                    bundle.getString("address")
                }
                else -> null
            }
            Log.e("TAG", "handleMessage: "+locationAddress )
        }
    }

}