package com.example.touradvisor.Activity

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.touradvisor.R
import com.example.touradvisor.databinding.ActivityCartBinding
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject


class CartActivity : AppCompatActivity(), PaymentResultListener  {
    lateinit var binding: ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityCartBinding.inflate(layoutInflater)
        workingClass()
        setContentView(binding.root)
    }

    private fun workingClass() {

        binding.imgBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnPay.setOnClickListener {

var price = 4399
            // rounding off the amount.
            val amount = Math.round(price.toFloat() * 100)

            // initialize Razorpay account.
            val checkout = Checkout()

            // set your id as below
            checkout.setKeyID("rzp_test_9J8ic3N0qG22IG")

            // set image

            // initialize json object
            val `object` = JSONObject()
            try {
                // to put name
                `object`.put("name", "TourAdvisor")

                // put description
                `object`.put("description", "Test payment")

                // to set theme color
                `object`.put("theme.color", "")

                // put the currency
                `object`.put("currency", "INR")

                // put amount
                `object`.put("amount", amount)

                // put mobile number
                `object`.put("prefill.contact", "9284064503")

                // put email
                `object`.put("prefill.email", "chaitanyamunje@gmail.com")

                // open razorpay to checkout activity
                checkout.open(this@CartActivity, `object`)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

    }

    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this, "Payment is successful", Toast.LENGTH_SHORT).show()

    }

    override fun onPaymentError(p0: Int, p1: String?) {

    }


}
