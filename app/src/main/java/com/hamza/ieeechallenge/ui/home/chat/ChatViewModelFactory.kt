package com.hamza.ieeechallenge.ui.home.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hamza.ieeechallenge.data.firebase.FirebaseSourceUser
import com.hamza.ieeechallenge.data.repositories.UserRepository

class ChatViewModelFactory(private val userRepository: FirebaseSourceUser) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserRepository::class.java)) {
            return UserRepository(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}