package com.hamza.ieeechallenge.data.repositories

import com.hamza.ieeechallenge.data.firebase.FirebaseSourceChat
import com.hamza.ieeechallenge.data.model.Conversation
import javax.inject.Inject

class ChatRepository @Inject constructor(private val firebase: FirebaseSourceChat) {

    fun getConversation(uid: String) = firebase.getConversation(uid)

    suspend fun sendConversationAsSender(senderId: String, receiverId: String, conversation: Conversation) = firebase.sendConversationAsSender(senderId, receiverId, conversation)

    suspend fun sendConversationToReceiver(senderId: String, receiverId: String, conversation: Conversation) = firebase.sendConversationToReceiver(senderId, receiverId, conversation)

}