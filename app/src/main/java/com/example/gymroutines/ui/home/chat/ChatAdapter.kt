package com.example.gymroutines.ui.home.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.R
import com.example.gymroutines.databinding.ChatItemBinding
import com.example.gymroutines.model.Messages

class ChatAdapter : ListAdapter<Messages, ChatAdapter.ViewHolder>(MessageDiff) {
    class ViewHolder(private val binding: ChatItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Messages) {
            binding.tvText.text = message.text
            binding.tvUserName.text = message.userName
            when (message.imageUrl) {
                "perfil1" -> binding.ivProfilePic.setImageResource(R.drawable.perfil1)
                "perfil2" -> binding.ivProfilePic.setImageResource(R.drawable.perfil2)
                "perfil3" -> binding.ivProfilePic.setImageResource(R.drawable.perfil3)
                else -> binding.ivProfilePic.setImageResource(R.drawable.perfil4)
            }
        }
    }

    object
    MessageDiff : DiffUtil.ItemCallback<Messages>() {
        override fun areItemsTheSame(oldItem: Messages, newItem: Messages): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Messages, newItem: Messages): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ChatItemBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}