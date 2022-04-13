package com.hamza.ieeechallenge.ui.home.contact

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.hamza.ieeechallenge.R
import com.hamza.ieeechallenge.databinding.ActivityContactBinding
import com.hamza.ieeechallenge.ui.home.chat.chatroom.ChatRoomActivity
import com.hamza.ieeechallenge.ui.profile.UserViewModel
import com.hamza.ieeechallenge.utils.Resource

class ContactActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityContactBinding = DataBindingUtil.setContentView(this, R.layout.activity_contact)

        viewModel.getContact()
        val contactAdapter = ContactAdapter(ContactAdapter.ContactListener {
            Log.i("MYTAG", "clickced")
            val intent = Intent(this, ChatRoomActivity::class.java)
            intent.putExtra("user", it)
            startActivity(intent)
        })

        viewModel.contactResponse.observe(this, Observer { response ->
            when(response) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    response.data?.let { userList ->
                        Log.i("MYTAG", "contact $userList")
                        contactAdapter.submitList(userList)
                        binding.rvContact.adapter = contactAdapter
                    }
                }
                is Resource.Error -> {}
            }
        })
    }
}