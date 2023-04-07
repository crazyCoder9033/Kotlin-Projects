package com.example.gulzaarsahabkishayari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Handler().postDelayed({
            val q = Intent(this@MainActivity, DashBoardActivity::class.java)
            startActivity(q)
            finish()
        }, 1500)
    }
}