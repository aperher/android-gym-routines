package com.example.gymroutines.ui.Home.routineDetails


import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.databinding.ExercisesDetailItemBinding
import com.example.gymroutines.model.Messages
import com.example.gymroutines.model.RoutineDetail

class RoutineDetailExercisesAdapter(): ListAdapter<RoutineDetail.ExercisesDetail, RoutineDetailExercisesAdapter.ViewHolder>(ExercisesDetailDiff) {
    class ViewHolder(private val binding: ExercisesDetailItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(routineDetail: RoutineDetail.ExercisesDetail) {
            binding.tvExerciseName.text = routineDetail.name
           //binding.tvExerciseReps.text = routineDetail.series
        }
    }

    object
    ExercisesDetailDiff : DiffUtil.ItemCallback<RoutineDetail.ExercisesDetail>() {
        override fun areItemsTheSame(oldItem: RoutineDetail.ExercisesDetail, newItem: RoutineDetail.ExercisesDetail): Boolean {
            return oldItem.name== newItem.name;
        }

        override fun areContentsTheSame(oldItem: RoutineDetail.ExercisesDetail, newItem: RoutineDetail.ExercisesDetail): Boolean {
            return oldItem == newItem;
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ExercisesDetailItemBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        );
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position));
    }



}