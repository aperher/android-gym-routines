package com.example.gymroutines.ui.Home.routines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.databinding.RoutineItemBinding
import com.example.gymroutines.model.SliderItem

class RoutinesSliderAdapter(private val onItemClicked: (idRoutine: String) -> Unit) : ListAdapter<SliderItem.RoutineItem, RoutinesSliderAdapter.ViewHolder>(RoutineDiff) {
    object RoutineDiff : DiffUtil.ItemCallback<SliderItem.RoutineItem>() {
        override fun areItemsTheSame(oldItem: SliderItem.RoutineItem, newItem: SliderItem.RoutineItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SliderItem.RoutineItem, newItem: SliderItem.RoutineItem): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: RoutineItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val routine = getItem(position)
                    onItemClicked(routine.id)
                }
            }
        }
        fun bind(routine: SliderItem.RoutineItem) {
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