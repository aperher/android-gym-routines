package com.example.gymroutines.ui.Home.routines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.databinding.RoutineItemBinding
import com.example.gymroutines.databinding.RoutineSliderItemBinding
import com.example.gymroutines.model.RoutineItem
import com.example.gymroutines.model.SliderItem

class RoutinesSliderAdapter : ListAdapter<RoutineItem, RoutinesSliderAdapter.ViewHolder>(RoutineDiff) {
    object RoutineDiff : DiffUtil.ItemCallback<RoutineItem>() {
        override fun areItemsTheSame(oldItem: RoutineItem, newItem: RoutineItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RoutineItem, newItem: RoutineItem): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: RoutineItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(routine: RoutineItem) {
            binding.title.text = routine.title
            //binding.image.setImageResource(routine.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RoutineItemBinding =
            RoutineItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}