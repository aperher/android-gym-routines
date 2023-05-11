package com.example.gymroutines.ui.Home.routineDetails


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.databinding.ExercisesDetailItemBinding
import com.example.gymroutines.model.RoutineDetail

class RoutineDetailExercisesAdapter(): ListAdapter<RoutineDetail.ExercisesDetail, RoutineDetailExercisesAdapter.RoutineExercisesViewHolder>(ExercisesDetailDiff) {
    inner class RoutineExercisesViewHolder(private val binding: ExercisesDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(routineDetail: RoutineDetail.ExercisesDetail) {
            binding.tvExerciseName.text = routineDetail.name
           //binding.tvExerciseReps.text = routineDetail.series
            Log.d("Exercise:", routineDetail.name)

        }
    }

    object
    ExercisesDetailDiff : DiffUtil.ItemCallback<RoutineDetail.ExercisesDetail>() {
        override fun areItemsTheSame(
            oldItem: RoutineDetail.ExercisesDetail,
            newItem: RoutineDetail.ExercisesDetail
        ): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(
            oldItem: RoutineDetail.ExercisesDetail,
            newItem: RoutineDetail.ExercisesDetail
        ): Boolean {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineExercisesViewHolder {
        Log.d("CreateViewHolder:", "Exercises")
        return RoutineExercisesViewHolder(ExercisesDetailItemBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false));
    }



    override fun onBindViewHolder(holder: RoutineExercisesViewHolder, position: Int) {
        holder.bind(getItem(position));
    }



}