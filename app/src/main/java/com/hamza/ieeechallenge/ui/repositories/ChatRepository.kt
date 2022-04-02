package com.hamza.ieeechallenge.ui.repositories

import com.hamza.ieeechallenge.ui.data.firebase.SourceChat
import com.hamza.ieeechallenge.ui.data.model.Conversation
import javax.inject.Inject

class ChatRepository @Inject constructor(private val firebase: SourceChat) {

    fun getConversation(uid: String) = firebase.getConversation(uid)

    suspend fun sendConversationAsSender(senderId: String, receiverId: String, conversation: Conversation) = firebase.sendConversationAsSender(senderId, receiverId, conversation)

    suspend fun sendConversationToReceiver(senderId: String, receiverId: String, conversation: Conversation) = firebase.sendConversationToReceiver(senderId, receiverId, conversation)

}