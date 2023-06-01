package com.example.touradvisor.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.touradvisor.Activity.AllDetailsActivity
import com.example.touradvisor.Adapter.SearchAdapter
import com.example.touradvisor.Adapter.SuratHotelAdapter
import com.example.touradvisor.ModelClass.SearchModelClass
import com.example.touradvisor.ModelClass.SuratModelClass
import com.example.touradvisor.ModelClass.TopDestinationModelClass
import com.example.touradvisor.databinding.FragmentSearchBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SearchFragment : Fragment() {
    lateinit var adapter: SearchAdapter

    lateinit var searchBinding: FragmentSearchBinding
    lateinit var firebaseDatabase: DatabaseReference
    lateinit var suratHotelAdapter: SuratHotelAdapter
    var suratDetailsList = ArrayList<SuratModelClass>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        searchBinding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        firebaseDatabase = FirebaseDatabase.getInstance().getReference()
        searchFeature()
        return searchBinding.root
    }

    private fun searchFeature() {

//        searchBinding.btnSearch.setOnClickListener {
//
//            var search=searchBinding.edtSearch.text.toString()
//
//            firebaseDatabase.child("top").child(search).child("hotel").addValueEventListener(object : ValueEventListener{
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    searchList.clear()
//                    for (photo in snapshot.children)
//                    {
//                        var data = photo.getValue(SuratModelClass::class.java)
//                        data?.let { it1 -> searchList.add(it1) }
////                        data?.location=photo.child("hotelName").value.toString()
//                    }
//                    adapter= SearchAdapter(searchList)
//                    var manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//                    searchBinding.rcvSearch.layoutManager = manager
//                    searchBinding.rcvSearch.adapter=adapter
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//
//                }
//            })
//        }
//

        searchBinding.txtHotels.setOnClickListener {

            var search=searchBinding.edtSearch.text.toString()

            firebaseDatabase.child("top").child(search).child("hotel").addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    suratDetailsList.clear()
                    for (photo in snapshot.children)
                    {
                        var data = photo.getValue(SuratModelClass::class.java)
                        data?.let { it1 -> suratDetailsList.add(it1) }
//                        data?.location=photo.child("hotelName").value.toString()
                    }
                    suratHotelAdapter= SuratHotelAdapter(suratDetailsList,context)
                    {
                        var intent = Intent(context, AllDetailsActivity::class.java)
                        intent.putExtra("name",it.name)
                        intent.putExtra("value",search)
                        intent.putExtra("key",it.key)
                        intent.putExtra("hotel",true)

                        startActivity(intent)
                    }
                    var manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    searchBinding.rcvSearch.layoutManager = manager
                    searchBinding.rcvSearch.adapter=suratHotelAdapter
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }

        searchBinding.txtPlaces.setOnClickListener {

            var search=searchBinding.edtSearch.text.toString()

            firebaseDatabase.child("top").child(search).child("place").addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    suratDetailsList.clear()
                    for (photo in snapshot.children)
                    {
                        var data = photo.getValue(SuratModelClass::class.java)
                        data?.let { it1 -> suratDetailsList.add(it1) }
//                        data?.location=photo.child("hotelName").value.toString()
                    }
                    suratHotelAdapter= SuratHotelAdapter(suratDetailsList,context){
                        var intent = Intent(context, AllDetailsActivity::class.java)
                        intent.putExtra("name",it.name)
                        intent.putExtra("value",search)
                        intent.putExtra("key",it.key)
                        intent.putExtra("place",true)
                        startActivity(intent)
                    }
                    var manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    searchBinding.rcvSearch.layoutManager = manager
                    searchBinding.rcvSearch.adapter=suratHotelAdapter
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }

        searchBinding.txtActivities.setOnClickListener {

            var search=searchBinding.edtSearch.text.toString()

            firebaseDatabase.child("top").child(search).child("activity").addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    suratDetailsList.clear()
                    for (photo in snapshot.children)
                    {
                        var data = photo.getValue(SuratModelClass::class.java)
                        data?.let { it1 -> suratDetailsList.add(it1) }
//                        data?.location=photo.child("hotelName").value.toString()
                    }
                    suratHotelAdapter= SuratHotelAdapter(suratDetailsList,context)
                    {
                        var intent = Intent(context, AllDetailsActivity::class.java)
                        intent.putExtra("name",it.name)
                        intent.putExtra("value",search)
                        intent.putExtra("key",it.key)
                        intent.putExtra("activity",true)
                        startActivity(intent)
                    }
                    var manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    searchBinding.rcvSearch.layoutManager = manager
                    searchBinding.rcvSearch.adapter=suratHotelAdapter
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }






    }

}