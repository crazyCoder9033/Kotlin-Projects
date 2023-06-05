package com.example.smsbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.telephony.SmsMessage
import android.util.Log
import android.widget.Toast

class IncomingSms: BroadcastReceiver() {
    val sms: SmsManager = SmsManager.getDefault()
    override fun onReceive(context: Context?, intent: Intent?) {
        // Retrieves a map of extended data from the intent.
        val bundle = intent?.extras
        try {
            if (bundle != null) {
                val pdusObj = bundle["pdus"] as Array<Any>?
                for (i in pdusObj!!.indices) {
                    val currentMessage: SmsMessage =
                        SmsMessage.createFromPdu(pdusObj!![i] as ByteArray)
                    val phoneNumber: String = currentMessage.getDisplayOriginatingAddress()
                    val message: String = currentMessage.getDisplayMessageBody()
                    Log.i("SmsReceiver", "senderNum: $phoneNumber; message: $message")


                    // Show Alert
                    val duration = Toast.LENGTH_LONG
                    val toast = Toast.makeText(
                        context,
                        "senderNum: $phoneNumber, message: $message", duration
                    )
                    toast.show()
                } // end for loop
            } // bundle is null
        } catch (e: Exception) {
            Log.e("SmsReceiver", "Exception smsReceiver$e")
        }
    }
}