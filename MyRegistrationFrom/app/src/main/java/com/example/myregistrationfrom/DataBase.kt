package com.example.myregistrationfrom

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.media.Image
import com.example.myregistrationfrom.ModelClass.UserDetailsModelClass

class DataBase(var context: Context) :SQLiteOpenHelper(context,"DetailsDb",null,1) {
    var userDetailList=ArrayList<UserDetailsModelClass>()
    override fun onCreate(db: SQLiteDatabase?) {
       var table="create table userDetailsTb(id_no integer primary key Autoincrement,fullName text,age text, gender text, address text , mobile text)"
        db?.execSQL(table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


    fun insertRecord(
        fullName: String,
        age: String,
        gender: String,
        address: String,
        mobile: String,

//        hobbies: StringBuilder
    )
    {
        var details=writableDatabase
        var adding = ContentValues()
        adding.put("fullName",fullName)
        adding.put("age",age)
        adding.put("gender",gender)
        adding.put("address",address)
        adding.put("mobile",mobile)

        details.insert("userDetailsTb",null,adding)
    }

    fun displayRecord(): ArrayList<UserDetailsModelClass> {
        if (userDetailList!=null)
        {
            userDetailList.clear()
        }
        var details = readableDatabase
        var table = "select * from userDetailsTb"
        var cursor=details.rawQuery(table,null)
        if (cursor.count>0)
        {
            cursor.moveToFirst()
            do {
                var id=cursor.getInt(0)
                var fullName=cursor.getString(1)
                var age=cursor.getString(2)
                var gender=cursor.getString(3)
                var address=cursor.getString(4)
                var mobile= cursor.getString(5)

                val model=UserDetailsModelClass(id,fullName,age,gender,address,mobile)
                userDetailList.add(model)

            }while (cursor.moveToNext())
        }
        return userDetailList
    }

    fun updateRecord(
        fullName: String,
        age: String,
        gender: String,
        address: String,
        mobile: String,
        id_number: Int
    ) {
        val update = writableDatabase
        val updateSql="update userDetailsTb set fullName='$fullName', age='$age',gender='$gender', address='$address',mobile='$mobile' where id_no='$id_number' "
        update.execSQL(updateSql)
    }

    fun deleteRecord(i: Int) {

        var delete=writableDatabase
        var sqlDelete="delete from userDetailsTb where id_no='$i' "
        delete.execSQL(sqlDelete)
    }

}