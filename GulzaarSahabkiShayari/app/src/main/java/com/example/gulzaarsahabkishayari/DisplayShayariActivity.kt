package com.example.gulzaarsahabkishayari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gulzaarsahabkishayari.Adapter.DisplayShayariAdapter
import com.example.gulzaarsahabkishayari.databinding.ActivityDisplayShayariBinding

class DisplayShayariActivity : AppCompatActivity() {
    lateinit var binding: ActivityDisplayShayariBinding
    var shayariText=ArrayList<ShayariDisplay>()
    lateinit var display:MyDatabase
    lateinit var adapter:DisplayShayariAdapter
    var getId=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDisplayShayariBinding.inflate(layoutInflater)
        setContentView(binding.root)
        display= MyDatabase(this)
        intview()
    }

    private fun intview() {
        var titleName: String? = intent.getStringExtra("title")
        binding.txtDisplayTitle.text=titleName

         getId=intent.getIntExtra("id",0)

//       shayariText= display.displayShayari(getId)
         adapter= DisplayShayariAdapter(
            {
                val intent = Intent(this@DisplayShayariActivity, QuoteDisplayActivity::class.java)
                intent.putExtra("shayari",it.shayari)
                startActivity(intent)
            },{  fav, sid ->

                display.favAdded(fav,sid)

        })


        var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcvDisplayShayari.layoutManager = manager
        binding.rcvDisplayShayari.adapter = adapter

//        shayariText= display.displayShayari(getId)

        binding.imgBack.setOnClickListener {
//
            onBackPressed()
        }
        binding.imgFavActivity.setOnClickListener {
            val intent = Intent(this@DisplayShayariActivity, FavouriteActivity::class.java)
            startActivity(intent)

        }
    }

    override fun onResume() {
        super.onResume()
        shayariText= display.displayShayari(getId)
        adapter.updateFunction(shayariText)
        Log.e("loggggg", "onResume: "+getId)
    }
}