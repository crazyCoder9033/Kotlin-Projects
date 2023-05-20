package com.example.aaglagadenge

import android.net.Uri

class StudentModelClass {

    lateinit var key: String
    lateinit var fullName: String
    lateinit var address: String
    lateinit var phone: String
    lateinit var email: String
    lateinit var images: String


    constructor
                (key : String, fullName: String, address:String, phone:String, email:String, images : String)
    {
        this.key=key
        this.fullName=fullName
        this.address=address
        this.phone=phone
        this.email=email
        this.images=images

                }

        constructor()
        {

        }

}