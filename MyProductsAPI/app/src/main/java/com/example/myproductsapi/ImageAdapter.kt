package com.example.myproductsapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import java.util.*
import kotlin.collections.ArrayList

class ImageAdapter(var images: ArrayList<String>) :PagerAdapter() {

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as ImageView
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var itemView: View = LayoutInflater.from(container.context).inflate(R.layout.image_slider_item, container, false)

        var imageView: ImageView = itemView.findViewById(R.id.imgView)

        Glide.with(container).load("${images[position]}").into(imageView)


        Objects.requireNonNull(container).addView(itemView)

        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView)
    }
}