package com.hamza.ieeechallenge.data.model


import java.util.*

data class Chat(
    val message: String,
    val timestamp: Long,
    val senderId: String,
    val receiverId: String
    )
