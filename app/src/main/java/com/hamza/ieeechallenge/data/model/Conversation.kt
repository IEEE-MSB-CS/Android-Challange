package com.hamza.ieeechallenge.data.model

data class Conversation(
    var chatWithId :String = "",
    var lastMessage : String = "",
    var timeStamp : Long = 0L ,
    var umReadChatCount : Int = 0
    )
