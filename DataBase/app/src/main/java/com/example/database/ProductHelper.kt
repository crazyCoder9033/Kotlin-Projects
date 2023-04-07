package com.example.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProductHelper(context: Context) : SQLiteOpenHelper(context,"ProductDb",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
       var sql="create table productTb(product_no integer primary key Autoincrement, name text, unit integer,price integer)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun dataSet(name: String, unit: String, price: String)
    {
        var db= writableDatabase
        var a =ContentValues()
        a.put("name",name)
        a.put("unit",unit)
        a.put("price",price)

        db.insert("productTb",null,a)


    }

}