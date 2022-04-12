package com.hamza.ieeechallenge.ui.home.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hamza.ieeechallenge.data.model.Conversation
import com.hamza.ieeechallenge.data.model.User
import com.hamza.ieeechallenge.databinding.ItemConversationBinding

class ChatConversationAdapter (private val listener: ConversationListener): ListAdapter<Conversation, ChatConversationAdapter.MyViewHolder>(DiffCallback) {

    private val userList = ArrayList<User>()

    companion object DiffCallback: DiffUtil.ItemCallback<Conversation>() {

        override fun areItemsTheSame(oldItem: Conversation, newItem: Conversation): Boolean {
            return oldItem.timestamp == newItem.timestamp
        }

        override fun areContentsTheSame(oldItem: Conversation, newItem: Conversation): Boolean {
            return oldItem == newItem
        }
    }

    fun setData(users: List<User>) {
        userList.clear()
        userList.addAll(users)
    }

    inner class MyViewHolder(val binding: ItemConversationBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(conversation: Conversation, user: User, listener: ConversationListener) {
            binding.clickListener = listener
            binding.user = user
            binding.conversation = conversation
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(ItemConversationBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val conversation = getItem(position)
        val user = userList[position]

        holder.bind(conversation, user, listener)

    }


    class ConversationListener(val clickListener: (conversation: Conversation) -> Unit) {
        fun onClick(conversation: Conversation) = clickListener(conversation)
    }

}