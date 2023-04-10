package com.example.gulzaarsahabkishayari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gulzaarsahabkishayari.databinding.ActivityFavouriteBinding

class FavouriteActivity : AppCompatActivity() {
    lateinit var fav:MyDatabase
    var FavouriteList=ArrayList<FavouriteShayariDisplay>()
    lateinit var adapter: FavAdapter
    lateinit var binding:ActivityFavouriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        fav= MyDatabase(this)

//        var text: String? = intent.getStringExtra("favShayari")
//        var variable =intent.getIntExtra("favv",-1)
//        var s_id =intent.getIntExtra("id",-1)
//        Log.e("check", "onCreate: "+variable +"--"+s_id)
//        binding.txtShayari.text=text
//        fav.favAdded(variable,s_id)
//      if (text != null) {
//            listShayari.add(text)
//        }
//        adapter=FavAdapter(listShayari)
//        var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        binding.rcvFavShayari.layoutManager = manager
//        binding.rcvFavShayari.adapter = adapter

        function()
    }

    private fun function() {

       FavouriteList= fav.FavouriteShayari()
        adapter=FavAdapter(FavouriteList){ favId ,sid ->

             fav.favAdded(favId,sid)
            adapter.updatedList(FavouriteList)

        }
        var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcvFavShayari.layoutManager = manager
        binding.rcvFavShayari.adapter = adapter


        binding.imgBack.setOnClickListener {
//            val intent = Intent(this@FavouriteActivity, DashBoardActivity::class.java)
//            startActivity(intent)
            onBackPressed()

        }




    }
}