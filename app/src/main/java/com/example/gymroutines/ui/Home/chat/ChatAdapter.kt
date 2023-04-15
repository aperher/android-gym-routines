package com.example.gymroutines.ui.Home.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.databinding.ChatItemBinding
import com.example.gymroutines.model.Messages

class ChatAdapter(): ListAdapter<Messages, ChatAdapter.ViewHolder>(MessageDiff) {
    class ViewHolder (private val binding: ChatItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Messages) {
            binding.tvText.text = message.text
            binding.tvUserName.text = message.userName
        }
    }
    object
    MessageDiff : DiffUtil.ItemCallback<Messages>() {
        override fun areItemsTheSame(oldItem: Messages, newItem: Messages): Boolean {
            return oldItem.id == newItem.id;
        }

        override fun areContentsTheSame(oldItem: Messages, newItem: Messages): Boolean {
            return oldItem == newItem;
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ChatItemBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false));
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position));
    }

}