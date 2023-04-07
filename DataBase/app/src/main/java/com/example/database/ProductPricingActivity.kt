package com.example.database

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import com.example.database.databinding.ActivityProductPricingBinding

class ProductPricingActivity : AppCompatActivity() {
    lateinit var product:ProductHelper


    lateinit var binding:ActivityProductPricingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityProductPricingBinding.inflate(layoutInflater)
        setContentView(binding.root)

         product = ProductHelper(this)

        intview()
        dynamic()
    }

    private fun dynamic() {

        val dynamicButton= Button(this)
        dynamicButton.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)


        dynamicButton.text="+"
        dynamicButton.setBackgroundColor(Color.BLUE)
        binding.loutAdd.addView(dynamicButton)

    }

    private fun intview() {

        binding.btnEnter.setOnClickListener {
            var name=binding.edtName.text.toString()
            var unit=binding.edtUnit.text.toString()
            var price=binding.edtPrice.text.toString()

            val unit1: Int=unit.toInt()
            val price1: Int=price.toInt()
            val total: Int= unit1 * price1

            binding.edtTotal.text=total.toString()

            product.dataSet(name,unit,price)
        }



    }
}