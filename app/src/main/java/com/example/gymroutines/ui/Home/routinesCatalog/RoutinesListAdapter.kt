package com.example.gymroutines.ui.Home.routinesCatalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.model.RoutinePreview
import com.example.gymroutines.databinding.RoutineItemBigBinding


class RoutinesListAdapter(private val onRoutineClicked: (idRoutine: String) -> Unit) :
    ListAdapter<RoutinePreview, RoutinesListAdapter.ViewHolder>(CatalogDiff) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RoutinesListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RoutineItemBigBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoutinesListAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: RoutineItemBigBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val routine = getItem(position)
                    onRoutineClicked(routine.id!!)
                }
            }
        }

        fun bind(routine: RoutinePreview) {
            binding.title.text = routine.title
            //binding.image.setImageResource(routine.image)
        }
    }

    object CatalogDiff : DiffUtil.ItemCallback<RoutinePreview>() {
        override fun areItemsTheSame(oldItem: RoutinePreview, newItem: RoutinePreview): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RoutinePreview, newItem: RoutinePreview): Boolean {
            return oldItem == newItem
        }
    }
}