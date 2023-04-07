package com.example.gulzaarsahabkishayari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gulzaarsahabkishayari.Adapter.Adapter
import com.example.gulzaarsahabkishayari.databinding.ActivityDashBoardBinding

class DashBoardActivity : AppCompatActivity() {
    lateinit var display:MyDatabase
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


        binding.imgFavActivity.setOnClickListener {
            val intent = Intent(this@DashBoardActivity, FavouriteActivity::class.java)
            startActivity(intent)
        }


    }



}