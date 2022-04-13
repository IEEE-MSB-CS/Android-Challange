package com.hamza.ieeechallenge.ui.home.chat

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.hamza.ieeechallenge.data.model.Conversation
import com.hamza.ieeechallenge.data.model.User
import com.hamza.ieeechallenge.data.repositories.ChatRepository
import com.hamza.ieeechallenge.data.repositories.UserRepository
import com.hamza.ieeechallenge.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val currentUser: FirebaseUser by lazy {
        firebaseAuth.currentUser!!
    }

    private val _conversationList = MutableLiveData<Resource<List<Conversation>>>()
    val conversationList: LiveData<Resource<List<Conversation>>>
        get() = _conversationList

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>>
        get() = _userList

    var userLists: MutableList<User> = ArrayList()
    var convList: List<Conversation> = ArrayList()

    init {
        Log.i("MYTAG", "init vm")
//        getConversation()
    }


    fun getConversation() {
        chatRepository.getConversation(currentUser.uid)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    return@addSnapshotListener
                }

                if (querySnapshot != null) {
                    convList = querySnapshot.toObjects<Conversation>()

                    for (conversation in convList) {
                        getUserByChatWithId(conversation.chatWithId)
                        Log.i("MYTAG", "looped ${conversation.lastMessage}")

                    }

                    Log.i("MYTAG", "result list ${convList.size}")



                    _conversationList.value = Resource.Success(convList)
                }

            }
    }

    private fun getUserByChatWithId(chatWithId: String){

        userRepository.getUserByChatWithId(chatWithId)
            .addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    Log.i("MYTAG", "error ${firebaseFirestoreException.message}")
                    return@addSnapshotListener
                }

                if (documentSnapshot != null && documentSnapshot.exists()) {
                    val result = documentSnapshot.toObject<User>()!!
                    //Log.i("MYTAG", "result ${result.username}")

                    if (userLists.size >= convList.size) {
                        userLists.clear()
                    }

//                    if (userLists.size < convList.size) {
                    userLists.add(result)
//                    }

                    if (userLists.size == convList.size) {
                        _userList.value = userLists.toList()
                    }




                }

            }

    }

    override fun onCleared() {
        super.onCleared()
        Log.i("MYTAG", "vm cleared")
    }
}