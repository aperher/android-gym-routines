package com.example.gymroutines.ui.home.routinesCatalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.R
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
            when(routine.imageURL){
                "gym1" -> binding.routineItemImg.setImageResource(R.drawable.gym1)
                "gym2" -> binding.routineItemImg.setImageResource(R.drawable.gym2)
                "gym3" -> binding.routineItemImg.setImageResource(R.drawable.gym3)
                else -> binding.routineItemImg.setImageResource(R.drawable.gym4)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RoutineItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}