package com.example.touradvisor.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.touradvisor.Activity.AllDetailsActivity
import com.example.touradvisor.ModelClass.SecondImageSliderModel
import com.example.touradvisor.R
import java.util.Objects

class ThirdImageAdapter(
    var imageList: ArrayList<SecondImageSliderModel>,
    var allDetailsActivity: AllDetailsActivity
) : PagerAdapter() {
    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val itemView: View = LayoutInflater.from(container.context).inflate(R.layout.image_slider_item, container, false)


        var imageView: ImageView = itemView.findViewById(R.id.imgView)

        Glide.with(allDetailsActivity).load(imageList[position].image).placeholder(R.drawable.baseline_image_24).into(imageView)
        Log.e("TAG", "instantiateItem: "+imageView )

        Objects.requireNonNull(container).addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}