package com.example.myregistrationfrom.Activity

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myregistrationfrom.Adapter.DashboardDetailsAdapter
import com.example.myregistrationfrom.DataBase
import com.example.myregistrationfrom.ModelClass.UserDetailsModelClass
import com.example.myregistrationfrom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var dbDisplay:DataBase
    lateinit var adapter : DashboardDetailsAdapter
    var userDetailList=ArrayList<UserDetailsModelClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
            dbDisplay= DataBase(this)
        appVersion()
        working()
    }

    private fun working() {

        binding.imgMenuButton.setOnClickListener {
            binding.appDrawer.openDrawer(GravityCompat.START)

            binding.loutAddDetails.setOnClickListener {
                val intent = Intent(this@MainActivity, AddFormActivity::class.java)
                startActivity(intent)
                binding.appDrawer.closeDrawer(GravityCompat.START)
        }

            binding.loutQuit.setOnClickListener {
                System.exit(0)
            }

            binding.loutHome.setOnClickListener {
                binding.appDrawer.closeDrawer(GravityCompat.START)
            }

        }


        userDetailList=dbDisplay.displayRecord()
         adapter= DashboardDetailsAdapter(userDetailList,{
            var iconUpdate="update"
            val intent = Intent(this@MainActivity, AddFormActivity::class.java)
            intent.putExtra("id",it.id)
            intent.putExtra("fullName",it.fullName)
            intent.putExtra("age",it.age)
            intent.putExtra("gender",it.gender)
            intent.putExtra("mobile",it.mobile)
            intent.putExtra("address",it.address)
            intent.putExtra("key_icon",iconUpdate)
            intent.putExtra("update",true)
            startActivity(intent)
            Log.e("idddd", "working: "+it.id)
        }, {
            userDetailList=dbDisplay.displayRecord()
            adapter.updatedData(userDetailList)
                dbDisplay.deleteRecord(it)
        },{
             val intent = Intent(this@MainActivity, DisplayUserDetailsActivity::class.java)
             intent.putExtra("id",it.id)
             intent.putExtra("fullName",it.fullName)
             intent.putExtra("age",it.age)
             intent.putExtra("gender",it.gender)
             intent.putExtra("mobile",it.mobile)
             intent.putExtra("address",it.address)
             startActivity(intent)
         })


        var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcvDisplayInformation.layoutManager = manager
        binding.rcvDisplayInformation.adapter = adapter


        userDetailList=dbDisplay.displayRecord()
        adapter.updatedData(userDetailList)



//      APP VERSION
        val pack:PackageManager=packageManager
        val info: PackageInfo=pack.getPackageInfo(
            packageName,0
        )
        val version: String=info.versionName
        binding.txtVersion.text=version


    }


    private fun appVersion() {

//        val version =
//            "Version Name : " + BuildConfig.VERSION_NAME + "\n" + "Version Code : " + BuildConfig.VERSION_CODE.toString()

//        binding.txtVersion.text = version

    }
}