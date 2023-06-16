package com.example.touradvisor.Fragment
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.touradvisor.Activity.CartActivity
import com.example.touradvisor.Activity.DisplayActivity
import com.example.touradvisor.Adapter.ImageAdapter
import com.example.touradvisor.Adapter.TopDestinationAdapter
import com.example.touradvisor.ModelClass.ImageSliderModel
import com.example.touradvisor.ModelClass.TopDestinationModelClass
import com.example.touradvisor.R
import com.example.touradvisor.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {
lateinit var imageAdapter: ImageAdapter
     lateinit var adapter : TopDestinationAdapter
    lateinit var homeBinding: FragmentHomeBinding
    private lateinit var auth: FirebaseAuth
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
        auth = Firebase.auth
        imageSlider()
        topDestination()
        workingClass()
        addToCart()
        appDrawer()
        userPhoto()


        Log.e("two", "topDestination: " )
        return homeBinding.root

    }

    private fun userPhoto() {


        homeBinding.imgUser.setOnClickListener {
            val newGamefragment = ProfileFragment()
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.fragmentDisplay, newGamefragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }


        auth.currentUser?.let {
            firebaseDatabase.child("user").child(it.uid).addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    var image = snapshot.child("images").value.toString()
                    Glide.with(this@HomeFragment).load(image).into(homeBinding.imgUser)

                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
        }
    }

    private fun appDrawer() {

        homeBinding.imgMenu.setOnClickListener {
            homeBinding.AppDrawer.openDrawer(GravityCompat.START)
        }

        auth.currentUser?.let {
            firebaseDatabase.child("user").child(it.uid).addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var name = snapshot.child("name").value.toString()
                    var email = snapshot.child("email").value.toString()
                    homeBinding.txtUsername.setText(name)
                    homeBinding.txtEmail.setText(email)
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })
        }

        homeBinding.loutPrivacyPolicy.setOnClickListener {
            val url = "https://himanshuxoxo.blogspot.com/2023/06/privacy-policy.html"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
            homeBinding.AppDrawer.openDrawer(GravityCompat.START)
        }

        homeBinding.loutShare.setOnClickListener {
            val a = Intent(Intent.ACTION_SEND)
            a.type = "text/plain"
            a.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL")
            a.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.tripadvisor.tripadvisor&hl=en-IN")
            startActivity(Intent.createChooser(a, "Share URL"))

            homeBinding.AppDrawer.openDrawer(GravityCompat.START)


        }

        homeBinding.loutQuit.setOnClickListener {
            System.exit(0)
        }

//        APP VERSION
        val pack: PackageManager =requireActivity().packageManager
        val info: PackageInfo =pack.getPackageInfo(requireActivity().packageName,0)
        val version: String=info.versionName
        homeBinding.txtVersion.text=version


    }

    fun addToCart()
    {
        homeBinding.btnCart.setOnClickListener {
            var intent = Intent(context,CartActivity::class.java)
            startActivity(intent)
        }



    }


    private fun workingClass() {

        auth.currentUser?.let {
            firebaseDatabase.child("user").child(it.uid)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var name = snapshot.child("name").value.toString()

                        homeBinding.txtName.setText(name)

                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })

        }
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

                    val newGamefragment = TopCityFragment()
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

//        homeBinding.loutCity.setOnClickListener {
//            Log.e("hhhh", "imageSlider: " )
//        }

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
                homeBinding.wormDotsIndicator.attachTo(homeBinding.VPView)

            }
            override fun onCancelled(error: DatabaseError) {
            }
        })





    }

}