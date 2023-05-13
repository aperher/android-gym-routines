package com.example.gymroutines.ui.Home.exercises

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.databinding.ExerciseItemBinding
import com.example.gymroutines.model.Exercise

class ExercisesCatalogAdapter(private val onExerciseClicked: (exercise: Exercise) -> Unit) :
    ListAdapter<Exercise, ExercisesCatalogAdapter.ViewHolder>(ExercisesDiff) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExercisesCatalogAdapter.ViewHolder {
        return ViewHolder(
            ExerciseItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ExercisesCatalogAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object
    ExercisesDiff : DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: ExerciseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val exercise = getItem(position)
                    onExerciseClicked(exercise)
                    //binding.cbExercise.isChecked = !binding.cbExercise.isChecked
                }
            }
        }

        fun bind(exercise: Exercise) {
            binding.tvExerciseName.text = exercise.name
            binding.tvExerciseInfo.text = exercise.equipment + ". " + exercise.primaryMuscles
        }
    }
}