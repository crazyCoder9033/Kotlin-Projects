package com.example.touradvisor.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.touradvisor.Activity.AllDetailsActivity
import com.example.touradvisor.Adapter.SuratHotelAdapter
import com.example.touradvisor.ModelClass.SuratModelClass
import com.example.touradvisor.databinding.FragmentSuratActivityBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SuratActivityFragment : Fragment() {
    lateinit var firebaseDatabase: DatabaseReference
    var suratDetailsList = ArrayList<SuratModelClass>()
    lateinit var suratHotelAdapter: SuratHotelAdapter

    lateinit var suratActivityBinding: FragmentSuratActivityBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        suratActivityBinding=FragmentSuratActivityBinding.inflate(layoutInflater,container,false)
        firebaseDatabase = FirebaseDatabase.getInstance().getReference()
        workingClass()
        return suratActivityBinding.root
    }

    private fun workingClass() {
        val value = arguments?.getString("key").toString()
        firebaseDatabase.child("top").child(value).child("activity").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                suratDetailsList.clear()
                for (photo in snapshot.children)
                {
                    var data = photo.getValue(SuratModelClass::class.java)
                    data?.let { it1 -> suratDetailsList.add(it1) }

//                    data?.thumbnail=photo.child("thumbnail").value.toString()
//                    data?.location=photo.child("location").value.toString()
                    suratHotelAdapter= SuratHotelAdapter(suratDetailsList,context){
                        var intent = Intent(context, AllDetailsActivity::class.java)
                        intent.putExtra("name",it.name)
                        intent.putExtra("value",value)
                        intent.putExtra("key",it.key)
                        intent.putExtra("activity",true)
                        startActivity(intent)
                    }
                    var manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    suratActivityBinding.rcvSuratActivity.layoutManager = manager
                    suratActivityBinding.rcvSuratActivity.adapter=suratHotelAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

}