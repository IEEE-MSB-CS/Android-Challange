package com.hamza.ieeechallenge.data.repositories

import android.net.Uri
import com.google.firebase.auth.PhoneAuthCredential
import com.hamza.ieeechallenge.data.firebase.FirebaseSourceUser
import javax.inject.Inject

class UserRepository @Inject constructor(private val firebase: FirebaseSourceUser) {

    suspend fun signInWithPhoneCredential(credential: PhoneAuthCredential) =
        firebase.signInWithPhoneCredential(credential)

    suspend fun getUser(phoneNumber: String?) = firebase.getUser(phoneNumber)

    suspend fun getUserList() = firebase.getUserList()

    suspend fun createUser(user: com.hamza.ieeechallenge.data.model.User, uid: String) = firebase.createUser(user, uid)

    suspend fun checkIfPhoneNumberAlreadyRegistered(phoneNumber: String?) =
        firebase.checkIfPhoneNumberAlreadyRegistered(phoneNumber)

    suspend fun uploadImage(uid: String, imageUri: Uri) = firebase.uploadAvatar(uid, imageUri)

    suspend fun loadExistedUser(uid: String) = firebase.loadExistedUser(uid)

    suspend fun getReceiverUidByPhoneNumber(phoneNumber: String) =
        firebase.getReceiverUidByPhoneNumber(phoneNumber)

    fun getUserByChatWithId(chatWithId: String) = firebase.getUserByChatWithId(chatWithId)

    suspend fun getUserTest(chatWithId: String) = firebase.getUserTest(chatWithId)
}