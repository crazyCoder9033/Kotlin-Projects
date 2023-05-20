package com.example.maptap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.os.Handler
import android.os.Message
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.akexorcist.googledirection.DirectionCallback
import com.akexorcist.googledirection.GoogleDirection
import com.akexorcist.googledirection.constant.AvoidType
import com.akexorcist.googledirection.constant.TransportMode
import com.akexorcist.googledirection.model.Direction
import com.akexorcist.googledirection.model.Route
import com.akexorcist.googledirection.util.DirectionConverter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.maptap.databinding.ActivityMaps2Binding
import com.google.android.gms.maps.model.*
import java.io.IOException

class MapsActivity2 : FragmentActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    lateinit var myLocation: LatLng
    lateinit var mySecondLocation: LatLng
    var polyline: Polyline? = null
    lateinit var addeddMarker: Marker
    lateinit var LocationPin:String
    lateinit var geoCoder :Geocoder
    private lateinit var binding: ActivityMaps2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaps2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        workingClass()
    }

    private fun workingClass() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.SvStartingLocation.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val firstLocation = binding.SvStartingLocation.query.toString()
                var addressList: List<Address>? = null

                if (firstLocation != null || firstLocation == "") {
                    geoCoder = Geocoder(this@MapsActivity2)
                    try {
                        addressList = geoCoder.getFromLocationName(firstLocation, 1)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    var address = addressList!![0]

                   myLocation = LatLng(address.latitude, address.longitude)
                    addeddMarker = mMap.addMarker(MarkerOptions().position(myLocation).title(firstLocation))!!
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 5f))
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        binding.SvDestinationLocation.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val secondLocation=binding.SvDestinationLocation.query.toString()
                var addressList : List<Address>?=null
                if (secondLocation!=null || secondLocation=="")
                {
                    geoCoder=Geocoder(this@MapsActivity2)
                    try {
                        addressList=geoCoder.getFromLocationName(secondLocation,1)
                    }catch (e : IOException){
                        e.printStackTrace()
                    }
                    var secondAddress=addressList!![0]
                    mySecondLocation= LatLng(secondAddress.latitude,secondAddress.longitude)

                    drawMarker(mySecondLocation,false)
                    drawline(mySecondLocation.latitude,mySecondLocation.longitude,TransportMode.DRIVING)
                    addeddMarker= mMap.addMarker(MarkerOptions().position(mySecondLocation).title(secondLocation))!!
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mySecondLocation,5f))
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
//
//        // Add a marker in Sydney and move the camera
//         myLocation = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(myLocation).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
//        getLatLong()

        mMap.setOnMapClickListener {
            drawMarker(it,false)
            drawline(it.latitude,it.longitude, TransportMode.DRIVING)
        }
    }



    private fun drawMarker(latLng: LatLng, flag: Boolean) {
        if (mMap != null) {
            mMap!!.clear()
            val markerOptions = MarkerOptions()
            markerOptions.position(latLng)
            //            markerOptions.title(title);
            if (flag) {
//                markerOptions.icon(BitmapFromVector(this, R.drawable.ic_map_event));
                markerOptions.icon(BitmapFromVector(this, R.drawable.backg))
            } else {
                Log.e("TAG", "drawMarker: mark")
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            }
            mMap!!.addMarker(markerOptions)
//                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//            mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
            mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5f))
        }
    }

    private fun BitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    private fun drawline(lat: Double, longt: Double, mode: String) {
        try {
//            drawMarker(LatLng(lat, longt), false)
            GoogleDirection.withServerKey("AIzaSyBv6cUUv3hbIEDcG69F297b37KqrTjepSg")
                .from(LatLng(myLocation!!.latitude, myLocation!!.longitude))
                .to(LatLng(lat, longt))
                .avoid(AvoidType.FERRIES)
                .avoid(AvoidType.HIGHWAYS)
                .transportMode(mode)
                .execute(object : DirectionCallback {
                    override fun onDirectionSuccess(direction: Direction?) {
                        directionsuccess(direction)
                    }
                    override fun onDirectionFailure(t: Throwable) {}
                })
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("TAG", "drawline:exce " + e.message)
        }
    }

    private fun directionsuccess(direction: Direction?) {
        try {
            if (direction!!.isOK) {
                val route = direction.routeList[0]
                if (route != null && !route.legList.isEmpty()) {
                    val distance = route.legList[0].distance
                    val duration = route.legList[0].duration
                    val directionPositionList = route.legList[0].directionPoint
                    if (!directionPositionList.isEmpty()) {
                        if (polyline != null) {
                            polyline!!.remove()
                        }
                        polyline = mMap!!.addPolyline(
                            DirectionConverter.createPolyline(this, directionPositionList, 4, ContextCompat.getColor(this, R.color.purple_200)
                            )
                        )
                        setCameraWithCoordinationBounds(route)
                    } else {
                        Toast.makeText(this, "noroute_available", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "noroute_available", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "noroute_available", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setCameraWithCoordinationBounds(route: Route) {
        val southwest = route.bound.southwestCoordination.coordination
        val northeast = route.bound.northeastCoordination.coordination
        val bounds = LatLngBounds(southwest, northeast)
        mMap!!.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))
    }

    private fun getLatLong() {
        val locationAddress = GeoCodeLocation()
        locationAddress.getAddressFromLocation(LocationPin, applicationContext, GeoCoderHandler())
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
            Log.e("TAG", "handleMessage: ==>" + locationAddress)
        }

    }
}