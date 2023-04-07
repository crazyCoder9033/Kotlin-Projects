package com.example.viewphew


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import java.util.*


class ImageAdapter(val imagelist: Array<Int>) : PagerAdapter() {


    override fun getCount(): Int {

        return imagelist.size

    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }


    @SuppressLint("MissingInflatedId")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {


        val itemView: View = LayoutInflater.from(container.context).inflate(R.layout.item, container, false)


        val imageView: ImageView = itemView.findViewById<View>(R.id.imgView) as ImageView



        imageView.setImageResource(imagelist[position])


        Objects.requireNonNull(container).addView(itemView)

        return itemView
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}