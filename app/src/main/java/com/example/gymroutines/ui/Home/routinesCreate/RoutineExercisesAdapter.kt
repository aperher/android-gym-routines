package com.example.gymroutines.ui.Home.routinesCreate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.databinding.RoutineExerciseItemBinding
import com.example.gymroutines.model.RoutineExercisePreview

class RoutineExercisesAdapter (private val onItemClicked: (idExercise: String) -> Unit) :
    ListAdapter<RoutineExercisePreview, RoutineExercisesAdapter.ViewHolder>(RoutineExerciseDiff) {
    object RoutineExerciseDiff : DiffUtil.ItemCallback<RoutineExercisePreview>() {
        override fun areItemsTheSame(oldItem: RoutineExercisePreview, newItem: RoutineExercisePreview): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RoutineExercisePreview, newItem: RoutineExercisePreview): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: RoutineExerciseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val exercise = getItem(position)
                    onItemClicked(exercise.id!!)
                }
            }
        }

        fun bind(exercise: RoutineExercisePreview) {
            binding.tvExerciseName.text = exercise.name
            //binding.tvExerciseSeries.text = exercise.series (POR HACER)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RoutineExerciseItemBinding =
            RoutineExerciseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}