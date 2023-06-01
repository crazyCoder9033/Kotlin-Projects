package com.example.touradvisor.ModelClass

class ImageSliderModel {

    lateinit var image : String
    lateinit var key : String
    lateinit var details:String
    lateinit var location : String
      var name : String?=null
    lateinit var open : String
    lateinit var spent : String


    constructor(image : String,key : String,details : String,location : String,name: String,open : String,spent : String )
    {
        this.image=image
        this.key=key
        this.details=details
        this.location=location
        this.name=name
        this.open=open
        this.spent=spent
    }

    constructor()
    {

    }
}