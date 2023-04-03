package com.example.gymroutines.ui.Home.routines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.databinding.RoutineSliderItemBinding
import com.example.gymroutines.model.SliderItem

class RoutinesAdapter : ListAdapter<SliderItem, RoutinesAdapter.ViewHolder>(SliderDiff) {
    object SliderDiff : DiffUtil.ItemCallback<SliderItem>() {
        override fun areItemsTheSame(oldItem: SliderItem, newItem: SliderItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SliderItem, newItem: SliderItem): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: RoutineSliderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(slider: SliderItem) {
            binding.tvSliderTitle.text = slider.title

            val adapter = RoutinesSliderAdapter()
            binding.recycleViewRoutines.adapter = adapter
            adapter.submitList(slider.routinesList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RoutineSliderItemBinding =
            RoutineSliderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}