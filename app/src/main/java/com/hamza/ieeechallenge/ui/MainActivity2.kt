package com.hamza.ieeechallenge.Activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.hamza.ieeechallenge.R
import com.hamza.ieeechallenge.R.layout.chat_screen

import android.widget.PopupMenu
import androidx.annotation.RequiresApi

import com.hamza.ieeechallenge.databinding.ChatScreenBinding
import com.hamza.ieeechallenge.ui.SettingBar
import com.hamza.ieeechallenge.ui.SettingsChatRecyclerView

class MainActivity2 : AppCompatActivity() {
    lateinit var binding : ChatScreenBinding
    lateinit var  popup : PopupMenu

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
        configureChatRecyclerView()
        SettingBar(this,binding)
    }

    private fun setContentView(){
    binding = DataBindingUtil.setContentView(this,chat_screen)
    }

    private fun configureChatRecyclerView(){
       SettingsChatRecyclerView(binding, this)
    }

   fun onClickMenu( view : View){
        popup = PopupMenu(this, binding.menuBt)
        popup.menuInflater.inflate(R.menu.menu, popup.menu)
        setOnMenuItemClickListener()
        popup.show()
   }

   private fun setOnMenuItemClickListener(){
       popup.setOnMenuItemClickListener { item ->
           true
       }
   }

}