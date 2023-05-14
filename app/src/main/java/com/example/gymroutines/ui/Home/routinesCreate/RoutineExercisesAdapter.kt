package com.example.gymroutines.ui.Home.routinesCreate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.databinding.RoutineExerciseItemBinding
import com.example.gymroutines.model.RoutineExercisePreview

class RoutineExercisesAdapter(private val onRoutineExerciseClicked: (exercise: RoutineExercisePreview) -> Unit) :
    ListAdapter<RoutineExercisePreview, RoutineExercisesAdapter.RoutineExerciseItemViewHolder>(RoutineExerciseDiff) {
    object RoutineExerciseDiff : DiffUtil.ItemCallback<RoutineExercisePreview>() {
        override fun areItemsTheSame(
            oldItem: RoutineExercisePreview,
            newItem: RoutineExercisePreview
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: RoutineExercisePreview,
            newItem: RoutineExercisePreview
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class RoutineExerciseItemViewHolder(private val binding: RoutineExerciseItemBinding) :
        ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val exercise = getItem(position)
                    onRoutineExerciseClicked(exercise)
                }
            }
        }

        fun bind(exercise: RoutineExercisePreview) {
            binding.tvExerciseName.text = exercise.name
            binding.tvExerciseSeries.text = exercise.series.joinToString(postfix = "x", separator = "x ")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineExerciseItemViewHolder {
        val binding: RoutineExerciseItemBinding =
            RoutineExerciseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoutineExerciseItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoutineExerciseItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}