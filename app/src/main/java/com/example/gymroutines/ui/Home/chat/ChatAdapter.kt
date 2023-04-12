package com.example.gymroutines.ui.Home.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.databinding.ChatItemBinding
import com.example.gymroutines.model.Messages

class ChatAdapter(private val itemclicked: ItemClicked): ListAdapter<Messages, ChatAdapter.ViewHolder>(MessageDiff) {
    class ViewHolder (private val binding: ChatItemBinding, itemclicked: ItemClicked): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                itemclicked.onClick(binding.root.toString())
            }
        }

        fun bind(messages: Messages) {

        }
    }
    object
    MessageDiff : DiffUtil.ItemCallback<Messages>() {
        override fun areItemsTheSame(oldItem: Messages, newItem: Messages): Boolean {
            return oldItem== newItem;
        }

        override fun areContentsTheSame(oldItem: Messages, newItem: Messages): Boolean {
            return oldItem == newItem;
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ChatItemBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false), itemclicked);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position));
    }
    interface ItemClicked { fun onClick(author: String) }

}