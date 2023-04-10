package com.example.gulzaarsahabkishayari

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.util.Log
//import com.example.externaldatabase.models.StdCode
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.lang.Error

class MyDatabase(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    private val mDataBase: SQLiteDatabase? = null
    private var mNeedUpdate = false
    private val mContext: Context

    init {
        if (Build.VERSION.SDK_INT >= 17) DB_PATH =
            context.applicationInfo.dataDir + "/databases/" else DB_PATH =
            "/data/data/" + context.packageName + "/databases/"
        mContext = context
        copyDataBase()
        this.getReadableDatabase()
    }

    private fun copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase()
            close()
            try {
                copyDBFile()
            } catch (mIOException: IOException) {
                throw Error("ErrorCopyingDataBase")
            }
        }
    }

    private fun checkDataBase(): Boolean {
        val dbFile = File(DB_PATH + DB_NAME)
        return dbFile.exists()
    }

    //    TODO copy file
    @Throws(IOException::class)
    private fun copyDBFile() {
        val mInput = mContext.assets.open(DB_NAME)
        val mOutput: OutputStream = FileOutputStream(DB_PATH + DB_NAME)
        val mBuffer = ByteArray(1024)
        var mLength: Int
        while (mInput.read(mBuffer).also { mLength = it } > 0) mOutput.write(mBuffer, 0, mLength)
        mOutput.flush()
        mOutput.close()
        mInput.close()
    }

    override fun onCreate(db: SQLiteDatabase) {}
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion) mNeedUpdate = true
    }

    //    TODO update database
    @Throws(IOException::class)
    fun updateDataBase() {
        if (mNeedUpdate) {
            val dbFile = File(DB_PATH + DB_NAME)
            if (dbFile.exists()) dbFile.delete()
            copyDataBase()
            mNeedUpdate = false
        }
    }

    @Synchronized
    override fun close() {
        if (mDataBase != null) mDataBase.close()
        super.close()
    }

    fun readData(): ArrayList<ShayariModelClass> {
        var shayariList=ArrayList<ShayariModelClass>()
        val db: SQLiteDatabase = getReadableDatabase()
        val sql = "select * from categoryTb"
        val c: Cursor = db.rawQuery(sql, null)


        if (c.moveToFirst()) {
            do {
                val id = c.getInt(0)
                val name = c.getString(1)

                Log.e(TAG, "readData:==> $id   $name")
                var model = ShayariModelClass(id ,name)
                shayariList.add(model)

            } while (c.moveToNext())
        }

        return shayariList
    }


    companion object {
        private const val TAG = "MyDatabase"
        private const val DB_NAME = "ShayariDatabase.db"
        private var DB_PATH = ""
        private const val DB_VERSION = 1
    }


    fun displayShayari(getId: Int): ArrayList<ShayariDisplay> {
        var shayariText=ArrayList<ShayariDisplay>()
        val db: SQLiteDatabase = getReadableDatabase()
        val sql = "select * from shayariTable where category_id='$getId'"
        val c: Cursor = db.rawQuery(sql, null)


        if (c.moveToFirst()) {
            do {
                val id = c.getInt(0)
                val name = c.getString(1)
                val cid = c.getInt(2)
                val fav = c.getInt(3)


//                Log.e(TAG, "readData:==> $id   $name $cid")
                var mode = ShayariDisplay(id ,name,cid,fav)
                shayariText.add(mode)

            } while (c.moveToNext())
        }

        return shayariText
    }

fun favAdded(variable: Int, s_id: Int)
{
    val update=writableDatabase
    val updateSql="update shayariTable set fav='$variable'where shayari_id='$s_id'"
    update.execSQL(updateSql)
}

    fun FavouriteShayari(): ArrayList<FavouriteShayariDisplay> {
        var FavList=ArrayList<FavouriteShayariDisplay>()
        val db: SQLiteDatabase = getReadableDatabase()
        val sql = "select * from shayariTable where fav=1 "
        val c: Cursor = db.rawQuery(sql, null)


        if (c.moveToFirst()) {
            do {
                val id = c.getInt(0)
                val name = c.getString(1)
                val fav = c.getInt(2)


//                Log.e(TAG, "readData:==> $id   $name $cid")
                var mode = FavouriteShayariDisplay(id ,name,fav)
                FavList.add(mode)

            } while (c.moveToNext())
        }
        return FavList
    }


    fun updatedFavList(variable: Int, favId: Int)
    {
        val update=writableDatabase
        val updateSql="select * from shayariTable where shayari_id='$variable' "
//        val updateSql="update shayariTable set fav='$variable'where shayari_id='$favId'"

        update.execSQL(updateSql)
    }

}