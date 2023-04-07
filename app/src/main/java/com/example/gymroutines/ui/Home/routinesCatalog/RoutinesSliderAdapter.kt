package com.example.gymroutines.ui.Home.routinesCatalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.databinding.RoutineItemBinding
import com.example.gymroutines.model.RoutinePreview

class RoutinesSliderAdapter(private val onItemClicked: (idRoutine: String) -> Unit) :
    ListAdapter<RoutinePreview, RoutinesSliderAdapter.ViewHolder>(RoutineDiff) {
    object RoutineDiff : DiffUtil.ItemCallback<RoutinePreview>() {
        override fun areItemsTheSame(oldItem: RoutinePreview, newItem: RoutinePreview): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RoutinePreview, newItem: RoutinePreview): Boolean {
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
                    onItemClicked(routine.id!!)
                }
            }
        }

        fun bind(routine: RoutinePreview) {
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