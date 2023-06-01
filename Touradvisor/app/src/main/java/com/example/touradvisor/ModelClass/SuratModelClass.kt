package com.example.touradvisor.ModelClass

class SuratModelClass {

    lateinit var amount : String
    lateinit var details : String
    lateinit var name : String
    lateinit var rating : String
    lateinit var thumbnail : String
    lateinit var key : String
    lateinit var location : String

    constructor(amount : String, details : String, name : String,rating : String,thumbnail : String,key : String, location : String)
    {
        this.amount=amount
        this.details=details
        this.name=name
        this.rating=rating
        this.thumbnail=thumbnail
        this.key=key
        this.location=location
    }
    constructor()
    {

    }
}