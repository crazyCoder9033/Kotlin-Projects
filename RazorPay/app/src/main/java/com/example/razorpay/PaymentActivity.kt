package com.example.razorpay

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.razorpay.Checkout
import com.razorpay.ExternalWalletListener
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import org.json.JSONException
import org.json.JSONObject


class PaymentActivity: Activity(), PaymentResultWithDataListener, ExternalWalletListener {
    // .....
    lateinit var amountEdt: EditText
    lateinit var payBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        /*
        * To ensure faster loading of the Checkout form,
        * call this method as early as possible in your checkout flow
        * */
        Checkout.preload(applicationContext)
        val co = Checkout()
        // apart from setting it in AndroidManifest.xml, keyId can also be set
        // programmatically during runtime
        co.setKeyID("Lu8a7AAd3lAYm7")
        startPayment()
        Checkout.clearUserData(this)

        amountEdt = findViewById(R.id.idEdtAmount);
        payBtn = findViewById(R.id.idBtnPay);



payBtn.setOnClickListener {

    val samount = amountEdt.getText().toString()
    val amount = Math.round(samount.toFloat() * 100)

    val checkout = Checkout()

    checkout.setKeyID("rzp_test_9J8ic3N0qG22IG");
    checkout.setImage(R.drawable.ic_launcher_background);
    val `object` = JSONObject()

    try {
        // to put name
        `object`.put("name", "Geeks for Geeks")

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
        checkout.open(this, `object`)
    } catch (e: JSONException) {
        e.printStackTrace()
    }


}

    }


    fun onPaymentSuccess(s: String) {
        // this method is called on payment success.
        Toast.makeText(this, "Payment is successful : $s", Toast.LENGTH_SHORT).show()
    }

    fun onPaymentError(i: Int, s: String) {
        // on payment failed.
        Toast.makeText(this, "Payment Failed due to error : $s", Toast.LENGTH_SHORT).show()
    }



    private fun startPayment() {
        /*
        *  You need to pass the current activity to let Razorpay create CheckoutActivity
        * */
        val activity:Activity = this
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name","Razorpay Corp")
            options.put("description","Demoing Charges")
            //You can omit the image option to fetch the image from the dashboard
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg")
            options.put("theme.color", "#3399cc");
            options.put("currency","INR");
            options.put("order_id", "order_DBJOWzybf0sJbb");
            options.put("amount","50000")//pass amount in currency subunits

            val retryObj =  JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("email","gaurav.kumar@example.com")
            prefill.put("contact","9876543210")

            options.put("prefill",prefill)
            co.open(activity,options)
        }catch (e: Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        Toast.makeText(this, "Payment Done", Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show()
    }

    override fun onExternalWalletSelected(p0: String?, p1: PaymentData?) {
        TODO("Not yet implemented")
    }


    //......
}