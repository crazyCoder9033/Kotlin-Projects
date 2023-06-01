package com.example.touradvisor.Fragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.touradvisor.Activity.CartActivity
import com.example.touradvisor.Activity.DisplayActivity
import com.example.touradvisor.Adapter.ImageAdapter
import com.example.touradvisor.Adapter.TopDestinationAdapter
import com.example.touradvisor.ModelClass.ImageSliderModel
import com.example.touradvisor.ModelClass.TopDestinationModelClass
import com.example.touradvisor.R
import com.example.touradvisor.databinding.FragmentHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HomeFragment : Fragment() {
lateinit var imageAdapter: ImageAdapter
     lateinit var adapter : TopDestinationAdapter
    lateinit var homeBinding: FragmentHomeBinding
    lateinit var firebaseDatabase: DatabaseReference

    var imageList = ArrayList<ImageSliderModel>()
    var topDestination = ArrayList<TopDestinationModelClass>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        firebaseDatabase = FirebaseDatabase.getInstance().getReference()
        imageSlider()
        topDestination()
        workingClass()
        addToCart()


        Log.e("two", "topDestination: " )
        return homeBinding.root

    }

    fun addToCart()
    {
        homeBinding.btnCart.setOnClickListener {
            var intent = Intent(context,CartActivity::class.java)
            startActivity(intent)
        }



    }


    private fun workingClass() {

//        homeBinding.txtKeepExploring.setOnClickListener {
//
//
//
//
//                        val newGamefragment = SuratFragment()
//                        val fragmentTransaction = requireFragmentManager().beginTransaction()
//                        fragmentTransaction.replace(R.id.fragmentDisplay, newGamefragment)
//                        fragmentTransaction.addToBackStack(null)
//                        fragmentTransaction.commit()
//
//
//        }


//        homeBinding.txtKeepExploring.setOnClickListener {
//            val newGamefragment = SuratFragment()
//            val fragmentTransaction = requireFragmentManager().beginTransaction()
//            fragmentTransaction.replace(R.id.fragmentDisplay, newGamefragment)
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()
//        }

    }

    private fun topDestination() {

        Log.e("one", "topDestination: " )

        firebaseDatabase.child("top").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                topDestination.clear()
                Log.e("TAG", "onDataChange: "+snapshot.childrenCount )

                for (photo in snapshot.children)
                {
                    var data = photo.getValue(TopDestinationModelClass::class.java)
                    data?.let { it1 -> topDestination.add(it1) }
                    data?.image=photo.child("image").value.toString()
                    data?.location=photo.child("location").value.toString()
                    data?.key=photo.child("key").value.toString()
                }

                adapter = TopDestinationAdapter(topDestination,this@HomeFragment) {

                    val args = Bundle()
                    args.putString("key",it.key)
                    args.putString("location",it.location)

                    val newGamefragment = SuratFragment()
                    newGamefragment.setArguments(args).toString()
                    val fragmentTransaction = requireFragmentManager().beginTransaction()
                    fragmentTransaction.replace(R.id.fragmentDisplay, newGamefragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()

                }

                var manager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                homeBinding.rcvTopDestination.layoutManager = manager

                homeBinding.rcvTopDestination.adapter=adapter


            }

            override fun onCancelled(error: DatabaseError) {
            }

        })


    }

    private fun imageSlider() {

        homeBinding.loutCity.setOnClickListener {
            Log.e("hhhh", "imageSlider: " )
        }

        firebaseDatabase.child("slider").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                imageList.clear()
                Log.e("TAG", "onDataChange: "+snapshot.childrenCount )
                for (photo in snapshot.children)
                {
                    var data = photo.getValue(ImageSliderModel::class.java)
                    data?.let { it1 -> imageList.add(it1) }
                    data?.image=photo.child("image").value.toString()
                }
//                imageAdapter.notifyDataSetChanged()
                imageAdapter= ImageAdapter(imageList,this@HomeFragment) {

                    var intent = Intent(context,DisplayActivity::class.java)
                    intent.putExtra("key",it.key)
                    startActivity(intent)


                }
                homeBinding.VPView.adapter=imageAdapter
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })



    }

}