package com.example.touradvisor.ModelClass

class SuratModelClass {

    lateinit var amount : String
    lateinit var details : String
    lateinit var name : String
    lateinit var rating : String
    lateinit var thumbnail : String
    lateinit var location : String
    lateinit var key : String
     var fav =0





    constructor(amount : String, details : String, name : String,rating : String,thumbnail : String,key : String, location : String,fav :Int)
    {
        this.amount=amount
        this.details=details
        this.name=name
        this.rating=rating
        this.thumbnail=thumbnail
        this.key=key
        this.location=location
        this.fav=fav




    }
    constructor()
    {

    }
}