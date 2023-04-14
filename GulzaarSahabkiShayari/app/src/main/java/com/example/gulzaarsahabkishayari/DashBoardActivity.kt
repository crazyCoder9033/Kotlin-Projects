package com.example.gulzaarsahabkishayari

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gulzaarsahabkishayari.Adapter.Adapter
import com.example.gulzaarsahabkishayari.databinding.ActivityDashBoardBinding

class DashBoardActivity : AppCompatActivity() {
    lateinit var display:MyDatabase
    var isBackPressedOnce=false
    var h=0
    var shayariList=ArrayList<ShayariModelClass>()
    lateinit var binding:ActivityDashBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        display= MyDatabase(this)
        working()
    }

    private fun working() {
       shayariList= display.readData()
        var adapter= Adapter(shayariList){

            val intent = Intent(this@DashBoardActivity, DisplayShayariActivity::class.java)
            intent.putExtra("title",it.categoryName)
            intent.putExtra("id",it.c_id)
            Log.e("IDDDDDDDDDDDDD", "working: "+it.c_id )

            startActivity(intent)
        }
        var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcvCategory.layoutManager = manager
        binding.rcvCategory.adapter = adapter


        binding.imgMenu.setOnClickListener {
            binding.AppDrawer.openDrawer(GravityCompat.START)
        }


        binding.loutFavourite.setOnClickListener {
            binding.AppDrawer.closeDrawer(GravityCompat.START)
            val intent = Intent(this@DashBoardActivity, FavouriteActivity::class.java)
            startActivity(intent)
        }

        binding.loutHome.setOnClickListener {
            binding.AppDrawer.closeDrawer(GravityCompat.START)
            Toast.makeText(this, "HOME PE HI HO", Toast.LENGTH_SHORT).show()
        }

//            var activity: MainActivity= MainActivity()
        binding.loutQuit.setOnClickListener {
//            activity.finish()
            System.exit(0)
        }

        binding.loutPrivacyPolicy.setOnClickListener {
            val url = "https://himanshuxoxo.blogspot.com/2023/04/privacy-policy.html"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
            binding.AppDrawer.closeDrawer(GravityCompat.START)
        }

        binding.loutShare.setOnClickListener {
            val a = Intent(Intent.ACTION_SEND)
            a.type = "text/plain"
            a.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL")
            a.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.infinitytechapps.allshayari.hindi.shayari")
            startActivity(Intent.createChooser(a, "Share URL"))
            binding.AppDrawer.closeDrawer(GravityCompat.START)
        }



    }

                override fun onBackPressed() {

            if (isBackPressedOnce)
            {
                super.onBackPressed()
                return
            }
                    Toast.makeText(this, "press back button twice", Toast.LENGTH_SHORT).show()
                    isBackPressedOnce=true

                    Handler().postDelayed({
                       isBackPressedOnce=false
                    }, 1500)
                }

}
