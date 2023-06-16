package com.example.touradvisor.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.touradvisor.Activity.AllDetailsActivity
import com.example.touradvisor.Adapter.SuratHotelAdapter
import com.example.touradvisor.ModelClass.SuratModelClass
import com.example.touradvisor.databinding.FragmentSearchBinding
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class SearchFragment : Fragment() {

//    lateinit var adapter: TabLayoutAdapter
    lateinit var searchBinding: FragmentSearchBinding
    lateinit var firebaseDatabase: DatabaseReference
    lateinit var suratHotelAdapter: SuratHotelAdapter
    var suratDetailsList = ArrayList<SuratModelClass>()
     var pos =0
    lateinit var search: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        searchBinding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        firebaseDatabase = FirebaseDatabase.getInstance().getReference()


        tabLayout()
        return searchBinding.root
    }


    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun tabLayout() {

        searchBinding.edtSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId==EditorInfo.IME_ACTION_SEARCH)
            {
                search=searchBinding.edtSearch.text.toString()
                hotel()
            }

            true
        }

        searchBinding.tbLayout.addTab(searchBinding.tbLayout.newTab().setText("Hotels"))
        searchBinding.tbLayout.addTab(searchBinding.tbLayout.newTab().setText("Places"))
        searchBinding.tbLayout.addTab(searchBinding.tbLayout.newTab().setText("Activity"))


        searchBinding.tbLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                 pos=tab.position
                Log.e("poss", "onTabSelected: "+pos )

                when(pos)
                {
                    0->{  hotel()  }

                    1-> { place() }

                    2-> { activity() }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        Log.e("TAG", "working: " )
        searchBinding.btnSearch.setOnClickListener {
            hideKeyboard(requireActivity())
             search=searchBinding.edtSearch.text.toString()
            hotel()
        }
    }
   fun hotel()
    {

        firebaseDatabase.child("top").child(search).child("hotel").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                suratDetailsList.clear()
                for (photo in snapshot.children)
                {
                    var data = photo.getValue(SuratModelClass::class.java)
                    data?.let { it1 -> suratDetailsList.add(it1) }
                }
                suratHotelAdapter= SuratHotelAdapter(suratDetailsList,context){
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

    fun place()
    {
//        searchBinding.VpLayout.currentItem = 1
        firebaseDatabase.child("top").child(search).child("place").addValueEventListener(object :
            ValueEventListener {
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

    fun activity()
    {
//        searchBinding.VpLayout.currentItem = 2
        firebaseDatabase.child("top").child(search).child("activity").addValueEventListener(object :
            ValueEventListener {
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

    private fun searchFeature() {
//
//
//        searchBinding.tbLayout.setOnClickListener {
//
//            var search=searchBinding.edtSearch.text.toString()
//
//            firebaseDatabase.child("top").child(search).child("hotel").addValueEventListener(object :
//                ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    suratDetailsList.clear()
//                    for (photo in snapshot.children)
//                    {
//                        var data = photo.getValue(SuratModelClass::class.java)
//                        data?.let { it1 -> suratDetailsList.add(it1) }
////                        data?.location=photo.child("hotelName").value.toString()
//                    }
//                    suratHotelAdapter= SuratHotelAdapter(suratDetailsList,context){
//                        var intent = Intent(context, AllDetailsActivity::class.java)
//                        intent.putExtra("name",it.name)
//                        intent.putExtra("value",search)
//                        intent.putExtra("key",it.key)
//                        intent.putExtra("hotel",true)
//
//                        startActivity(intent)
//                    }
//                    var manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//                    searchBinding.rcvSearch.layoutManager = manager
//                    searchBinding.rcvSearch.adapter=suratHotelAdapter
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//
//                }
//            })
//        }
//
//        searchBinding.txtPlaces.setOnClickListener {
//
//            var search=searchBinding.edtSearch.text.toString()
//
//            firebaseDatabase.child("top").child(search).child("place").addValueEventListener(object : ValueEventListener{
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    suratDetailsList.clear()
//                    for (photo in snapshot.children)
//                    {
//                        var data = photo.getValue(SuratModelClass::class.java)
//                        data?.let { it1 -> suratDetailsList.add(it1) }
////                        data?.location=photo.child("hotelName").value.toString()
//                    }
//                    suratHotelAdapter= SuratHotelAdapter(suratDetailsList,context){
//                        var intent = Intent(context, AllDetailsActivity::class.java)
//                        intent.putExtra("name",it.name)
//                        intent.putExtra("value",search)
//                        intent.putExtra("key",it.key)
//                        intent.putExtra("place",true)
//                        startActivity(intent)
//                    }
//                    var manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//                    searchBinding.rcvSearch.layoutManager = manager
//                    searchBinding.rcvSearch.adapter=suratHotelAdapter
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//
//                }
//            })
//        }
//
//        searchBinding.txtActivities.setOnClickListener {
//
//            var search=searchBinding.edtSearch.text.toString()
//
//            firebaseDatabase.child("top").child(search).child("activity").addValueEventListener(object : ValueEventListener{
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    suratDetailsList.clear()
//                    for (photo in snapshot.children)
//                    {
//                        var data = photo.getValue(SuratModelClass::class.java)
//                        data?.let { it1 -> suratDetailsList.add(it1) }
////                        data?.location=photo.child("hotelName").value.toString()
//                    }
//                    suratHotelAdapter= SuratHotelAdapter(suratDetailsList,context){
//
//                        var intent = Intent(context, AllDetailsActivity::class.java)
//                        intent.putExtra("name",it.name)
//                        intent.putExtra("value",search)
//                        intent.putExtra("key",it.key)
//                        intent.putExtra("activity",true)
//                        startActivity(intent)
//                    }
//                    var manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//                    searchBinding.rcvSearch.layoutManager = manager
//                    searchBinding.rcvSearch.adapter=suratHotelAdapter
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//
//                }
//            })
//        }
//
//
//
//
//
//
    }


}