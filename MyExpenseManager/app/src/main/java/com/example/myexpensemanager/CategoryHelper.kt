package com.example.myexpensemanager

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myexpensemanager.ModelClass.CategoryModelClass
import com.example.myexpensemanager.ModelClass.IncomeExpenseModelClass

class CategoryHelper(var context: Context) : SQLiteOpenHelper(context,"BudgetDb",null,1) {
    var list=ArrayList<CategoryModelClass>()
    var incomeExpenselist=ArrayList<IncomeExpenseModelClass>()
    override fun onCreate(db: SQLiteDatabase?) {
        var table1="create table categoryTb(category_no integer primary key Autoincrement,name text)"
        var table2="create table incomeExpenseTb(id_no integer primary key Autoincrement,date text,amount integer,category_name text,modeType text,type integer,note text)"

        db?.execSQL(table1)
        db?.execSQL(table2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
    fun insertRecord(name: String) {
        var dbb=writableDatabase
        var c= ContentValues()
        c.put("name",name)
        dbb.insert("categoryTb",null,c)
    }

    fun displayRecord(): ArrayList<CategoryModelClass> {
        if (list!= null)
        {
            list.clear()
        }

        var dbb=readableDatabase
        val table1="select * from categoryTb"
        var cursor=dbb.rawQuery(table1,null)
        if(cursor.count>0)
        {
            cursor.moveToFirst()
            do {
                val name=cursor.getString(1)
                val model= CategoryModelClass(name)
                list.add(model)
            }while (cursor.moveToNext())
        }
        return list
    }

    fun insertExpenseIncomeRecord(dateValue: String, amount: String,  selectedcategory: String, selectedMode: String, type: Int,notes: String)
    {
        var inExp=writableDatabase
        var adding = ContentValues()
        adding.put("date",dateValue)
        adding.put("amount",amount)
        adding.put("category_name",selectedcategory)
        adding.put("modeType",selectedMode)
        adding.put("type",type)
        adding.put("note",notes)
        inExp.insert("incomeExpenseTb",null,adding)
    }

    fun displayIncomeExpenseRecord(): ArrayList<IncomeExpenseModelClass> {

        if (incomeExpenselist!= null)
        {
            incomeExpenselist.clear()
        }

        var inExp=readableDatabase
        val table2="select * from incomeExpenseTb"
        var cursor=inExp.rawQuery(table2,null)
        if (cursor.count>0)
        {
            cursor.moveToFirst()
            do {
                var id=cursor.getInt(0)
                var date=cursor.getString(1)
                var amount=cursor.getString(2)
                var category=cursor.getString(3)
                var mode=cursor.getString(4)
                var type= cursor.getInt(5)
                var note=cursor.getString(6)

                val modelTwo=IncomeExpenseModelClass(id,date,amount,category,mode,type,note)
                incomeExpenselist.add(modelTwo)
            }while (cursor.moveToNext())
        }
        return incomeExpenselist
    }


    fun updateRecord(amount: String?,selectedcategory:String?,selectedMode:String?,type:Int?, notes: String?, id_number: Int)
    {
        val update=writableDatabase
        val updateSql="update incomeExpenseTb set amount='$amount',category_name='$selectedcategory',modeType='$selectedMode', type='$type',note='$notes' where id_no='$id_number'"
        update.execSQL(updateSql)
    }

    fun deleteRecord(id: Int)
    {
        var delete=writableDatabase
        var sqlDelete="delete from incomeExpenseTb where id_no='$id' "
        delete.execSQL(sqlDelete)
    }


}