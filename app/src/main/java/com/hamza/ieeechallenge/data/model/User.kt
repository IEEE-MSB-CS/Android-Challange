package com.hamza.ieeechallenge.data.model

data class User(
    var userName :String? = "",
    var phone :String? = "",
    var photoUrl : String? = null ,
    var isOneire : Boolean = false,
    var lastSeen : Long = 0L,
    var about :String = ""
    )
