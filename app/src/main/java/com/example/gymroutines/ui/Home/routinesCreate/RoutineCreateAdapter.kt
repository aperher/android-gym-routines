package com.example.gymroutines.ui.Home.routinesCreate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.databinding.HeaderCreateItemBinding
import com.example.gymroutines.databinding.RoutineExercisesBinding
import com.example.gymroutines.model.RoutineExercises

class RoutineCreateAdapter(
    private val onRoutineClicked: (idRoutine: String) -> Unit
) : ListAdapter<RoutineExercises, RecyclerView.ViewHolder>(ExerciseDiff) {
    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_EXERCISES = 1
    }

    object ExerciseDiff : DiffUtil.ItemCallback<RoutineExercises>() {
        override fun areItemsTheSame(oldItem: RoutineExercises, newItem: RoutineExercises): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: RoutineExercises, newItem: RoutineExercises): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val binding: HeaderCreateItemBinding =
                    HeaderCreateItemBinding.inflate(inflater, parent, false)
                HeaderViewHolder(binding)
            }
            else -> {
                val binding: RoutineExercisesBinding =
                    RoutineExercisesBinding.inflate(inflater, parent, false)
                ExercisesViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_HEADER -> {
                val headerViewHolder = holder as HeaderViewHolder
                headerViewHolder.bind()
            }
            VIEW_TYPE_EXERCISES -> {
                val exercisesViewHolder = holder as ExercisesViewHolder
                exercisesViewHolder.bind(getItem(position))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_HEADER
            else -> VIEW_TYPE_EXERCISES
        }
    }

    inner class ExercisesViewHolder(private val binding: RoutineExercisesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(routineExercises: RoutineExercises) {
            val adapter = RoutineExercisesAdapter(onRoutineClicked)
            binding.rvExercises.adapter = adapter
            adapter.submitList(routineExercises.exercisesPreview)
        }
    }

    class HeaderViewHolder(private val binding: HeaderCreateItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            // Se le podría pasar una lista de los filtros y meterlos en un recycler view
        }
    }

    override fun submitList(list: MutableList<RoutineExercises>?) {
        list?.add(0, RoutineExercises("", listOf())) // Header
        super.submitList(list)
    }
}