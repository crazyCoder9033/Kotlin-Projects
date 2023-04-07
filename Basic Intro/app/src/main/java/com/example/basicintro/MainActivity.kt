package com.example.basicintro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var name:String
    var sub1:Int=95
    var sub2:Int=55
    var sub3=94
    var sub4:Int=99
    var sub5:Int=96
    var total:Int=0
    var percent:Int=0

    var week:Int=4

     lateinit var btnWeek : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intview()
        whenStatement()
    }

    fun whenStatement()
    {
        btnWeek=findViewById(R.id.btnWeek)

        btnWeek.setOnClickListener {

            when (week) {
                1 -> {
                    Toast.makeText(this@MainActivity, "SUNDAY", Toast.LENGTH_SHORT).show()
                }

                2 -> {
                    Toast.makeText(this@MainActivity, "MONDAY", Toast.LENGTH_SHORT).show()
                }

                3 -> {
                    Toast.makeText(this@MainActivity, "TUESDAY", Toast.LENGTH_SHORT).show()
                }

                4 -> {
                    Toast.makeText(this@MainActivity, "WEDNESDAY", Toast.LENGTH_SHORT).show()
                }

                5 -> {
                    Toast.makeText(this@MainActivity, "THURSDAY", Toast.LENGTH_SHORT).show()
                }

                6 -> {
                    Toast.makeText(this@MainActivity, "FRIDAY", Toast.LENGTH_SHORT).show()
                }

                7 -> {
                    Toast.makeText(this@MainActivity, "SATURDAY", Toast.LENGTH_SHORT).show()
                }

                else -> {

                }


            }
        }

    }

    private fun intview() {
        var btnClick=findViewById<Button>(R.id.btnClick)
        var txtName=findViewById<TextView>(R.id.txtName)
        var txtTotal=findViewById<TextView>(R.id.txtTotal)
        var txtPercent=findViewById<TextView>(R.id.txtPercent)


            name="himanshu"
            total=sub1+sub2+sub3+sub4+sub5

            percent= total/5
            Log.e("TAG", "NAME  : "+name )
            Log.e("TAG", "TOTAL MARKS == :"+total )

            if(percent>=85 && percent<100)
            {
                Log.e("TAG", "GRADE A == : "+percent+"%" )
            }

            if(percent>=60 && percent<85)
            {
                Log.e("TAG", "GRADE B == : "+percent+"%" )
            }

            if(percent>=35 && percent<60)
            {
                Log.e("TAG", "GRADE C == : "+percent+"%" )
            }

        btnClick.setOnClickListener {
            Log.e("TAG", "Click: ")

            txtName.setText("NAME: " + name)
            txtTotal.setText("TOTAL MARKS: " + total)
            txtPercent.setText("PERCENTAGE: " + percent)

            Toast.makeText(this@MainActivity, "YAYYY", Toast.LENGTH_LONG).show()


        }


    }

}