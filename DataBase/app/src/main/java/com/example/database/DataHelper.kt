package com.example.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataHelper(context: Context) : SQLiteOpenHelper(context,"StudentDb",null,1) {
    var dataList=ArrayList<DataModelClass>()
    override fun onCreate(db: SQLiteDatabase?) {
        var sql="create table studentTb (Student_id integer primary key Autoincrement,name text, mobile text,gender text,fees integer)"
//        var sql2="create table professorTb(Professor_id integer primary key Autoincrement, name text, mobile text, gender text, salary integer)"
        db?.execSQL(sql)
//        db?.execSQL(sql2)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertRecord(name: String, mobile: String, gender: String, fees: String)
    {

        var dbb = writableDatabase
//        var s="insert into studentTb (name,mobile,gender,fees) values('himanshu','9033609969','male',75000)"
//        var s1="insert into studentTb (name,mobile,gender,fees) values('Shruti','999999999','female',100500)"
//        var s2="insert into studentTb (name,mobile,gender,fees) values('Venc','8888888888','female',65000)"
//        var s3="insert into studentTb (name,mobile,gender,fees) values('himanshu','9033609969','male',7500)"
//
//
//        var a1="insert into professorTb(name,mobile,gender,salary) values ('riddhi borda','11111111','female','100000')"
//        dbb.execSQL(s)
//        dbb.execSQL(s1)
//        dbb.execSQL(s2)
//        dbb.execSQL(s3)
//        dbb.execSQL(a1)


        var c= ContentValues()
        c.put("name",name)
        c.put("mobile",mobile)
        c.put("gender",gender)
        c.put("fees",fees)

        dbb.insert("studentTb",null,c)
    }

    fun displayRecord() : ArrayList<DataModelClass>
    {
        dataList.clear()
        val dbb=writableDatabase
        val sql="select * from studentTb"
        var cursor=dbb.rawQuery(sql,null)
        if(cursor.count > 0)
        {
            cursor.moveToFirst()
            do {
                val Student_id=cursor.getInt(0)
                val name=cursor.getString(1)
                val mobile=cursor.getString(2)
                val gender=cursor.getString(3)
                val fees=cursor.getInt(4)

                val model = DataModelClass(Student_id,name,mobile,gender,fees)
                dataList.add(model)


                Log.e("TAG", "displayRecord: "+Student_id+" "+name +" "+mobile+ " "+gender+ " "+fees )

            }while (cursor.moveToNext())
        }

        else
        {
            Log.e("TAG", "displayRecord: "+"no data found" )
        }
        return dataList
    }
}