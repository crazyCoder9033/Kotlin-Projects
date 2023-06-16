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
import com.example.touradvisor.Adapter.SuratHotelAdapter
import com.example.touradvisor.ModelClass.SuratModelClass
import com.example.touradvisor.databinding.FragmentTopcityfragmentBinding
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase


class TopCityFragment : Fragment() {
lateinit var suratFragment : FragmentTopcityfragmentBinding
    lateinit var firebaseDatabase: DatabaseReference
    lateinit var suratHotelAdapter: SuratHotelAdapter
    private lateinit var auth: FirebaseAuth
    var suratDetailsList = ArrayList<SuratModelClass>()
 var position=0
    var pos =0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       suratFragment=FragmentTopcityfragmentBinding.inflate(layoutInflater,container,false)
        firebaseDatabase = FirebaseDatabase.getInstance().getReference()
        auth = Firebase.auth
        tabLayout()

        suratFragment.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }



        val location = arguments?.getString("location").toString()

        suratFragment.txtTitle.setText(location)

        return suratFragment.root
    }

    private fun tabLayout() {
        hotel()
        suratFragment.tbLayout.addTab(suratFragment.tbLayout.newTab().setText("Hotels"))
        suratFragment.tbLayout.addTab(suratFragment.tbLayout.newTab().setText("Places"))
        suratFragment.tbLayout.addTab(suratFragment.tbLayout.newTab().setText("Activity"))


        suratFragment.tbLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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
       var   value = arguments?.getString("key").toString()

        firebaseDatabase.child("top").child(value).child("hotel").addValueEventListener(object :
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
                    intent.putExtra("value",value)
                    intent.putExtra("key",it.key)

                    intent.putExtra("hotel",true)
                    startActivity(intent)
                }
                var manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                suratFragment.rcvSuratHotel.layoutManager = manager
                suratFragment.rcvSuratHotel.adapter=suratHotelAdapter
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

    }

    fun place()
    {
        val value = arguments?.getString("key").toString()

        firebaseDatabase.child("top").child(value).child("place").addValueEventListener(object :
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
                    intent.putExtra("value",value)
                    intent.putExtra("key",it.key)
                    intent.putExtra("place",true)

                    startActivity(intent)
                }
                var manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                suratFragment.rcvSuratHotel.layoutManager = manager
                suratFragment.rcvSuratHotel.adapter=suratHotelAdapter
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    fun activity()
    {
        val value = arguments?.getString("key").toString()

        firebaseDatabase.child("top").child(value).child("activity").addValueEventListener(object :
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
                    intent.putExtra("value",value)
                    intent.putExtra("key",it.key)
                    intent.putExtra("activity",true)

                    startActivity(intent)
                }
                var manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                suratFragment.rcvSuratHotel.layoutManager = manager
                suratFragment.rcvSuratHotel.adapter=suratHotelAdapter
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }



}

class SaveDataModelCLass(
    var name: String,
    var fav: Int,
    var key: String,
    var amount: String,
    var details: String,
    var rating: String,
    var thumbnail: String,
    var location: String,

) {

}
