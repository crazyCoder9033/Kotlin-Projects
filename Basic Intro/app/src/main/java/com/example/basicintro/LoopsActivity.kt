package com.example.basicintro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class LoopsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loops)


        val numbers= arrayOf<Int>(11,22,333,4444,555,666,777)
        //        setting data in between means replacing

        numbers.set(3,999)
        for (i in numbers.indices) {
            Log.e("TAG", "onCreate: " +numbers[i])
        }



        val cars = arrayOf<String>("BMW","AUDI","MERCEDES","VOLVO")

//        setting data in between means replacing
       cars.set(2,"himanshu")
        for (j in cars.indices)
        {
            Log.e("TAG", "onCreate: "+cars[j])
        }


        /*val both= arrayOf("BMW","AUDI","MERCEDES","VOLVO",11,22,333,4444,555,666,777)
            for (a in both)
            {
                Log.e("TAG", "onCreate: "+a )
            }*/

        val list = ArrayList<String>()

        list.add("H")
        list.add("I")
        list.add("M")
        list.add("A")
        list.add("N")
        list.add("S")
        list.add("H")
        list.add("U")

        list.set(4,"HIMANSHU")

        for (l in list)
        {
            Log.e("TAG", "onCreate: "+l )
        }


        val listNumebrs= ArrayList<Int>()
        listNumebrs.add(999)
        listNumebrs.add(222)
        listNumebrs.add(888)
        listNumebrs.add(4)

        for (n in listNumebrs)
        {
            Log.e("TAG", "onCreate: "+listNumebrs)
        }



        val rollno= arrayOf(1,2,3,4,5,6,7,8,9)
        for (r in rollno.indices)
        {
            Log.e("TAG", ""+"rollno[+1$r]"+ rollno[r])
        }

//SHORTCUT
        for (i in 1.. 5)
        {
            Log.e("TAG", "onCreate: "+i )
        }
//        12345


        for (i in 5 downTo 1)
        {
            Log.e("TAG", "onCreate: "+i )
        }
//        54321

        for (i in 1..5 step 2)
        {
            Log.e("TAG", "onCreate: "+i)
        }
//        135

        for(i in 10 downTo 1 step 2)
        {
            Log.e("TAG", "onCreate: "+i )
        }
//        10 8 6 4 2

    }
}