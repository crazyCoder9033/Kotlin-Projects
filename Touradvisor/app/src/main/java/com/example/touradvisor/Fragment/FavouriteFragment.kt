package com.example.touradvisor.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.touradvisor.Activity.AllDetailsActivity
import com.example.touradvisor.Adapter.FavouriteAdapter
import com.example.touradvisor.ModelClass.SuratModelClass
import com.example.touradvisor.databinding.FragmentFavouriteBinding
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase


class FavouriteFragment : Fragment() {
    lateinit var favouriteBinding: FragmentFavouriteBinding
    lateinit var firebaseDatabase: DatabaseReference
    var pos =0
    private lateinit var auth: FirebaseAuth

    lateinit var adapter: FavouriteAdapter
    var suratDetailsList = ArrayList<SuratModelClass>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       favouriteBinding= FragmentFavouriteBinding.inflate(layoutInflater,container,false)
        firebaseDatabase = FirebaseDatabase.getInstance().getReference()
        auth = Firebase.auth
        hotel()
        workingClass()

        return favouriteBinding.root
    }



    private fun workingClass() {

        favouriteBinding.tbLayout.addTab(favouriteBinding.tbLayout.newTab().setText("Hotels"))
        favouriteBinding.tbLayout.addTab(favouriteBinding.tbLayout.newTab().setText("Places"))
        favouriteBinding.tbLayout.addTab(favouriteBinding.tbLayout.newTab().setText("Activity"))


        favouriteBinding.tbLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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
    }


    fun hotel()
    {

//        val key = arguments?.getString("key").toString()
//        val value = arguments?.getString("value").toString()




        adapter= FavouriteAdapter(context,{fav, key  ->

        }) {


        }
        var manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        favouriteBinding.rcvSearch.layoutManager = manager
        favouriteBinding.rcvSearch.adapter=adapter
        firebaseDatabase.child("user").child(auth.currentUser!!.uid).child("favourites").child("hotel").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
              for (i in snapshot.children)
              {
                  var data=i.getValue(SuratModelClass::class.java)
                  data?.let { it1 -> suratDetailsList.add(it1) }
              }
                adapter.updateList(suratDetailsList)

            }

                        override fun onCancelled(error: DatabaseError) {

                    }
        })
    }



    fun place()
    {

    }

    fun activity()
    {

    }

}