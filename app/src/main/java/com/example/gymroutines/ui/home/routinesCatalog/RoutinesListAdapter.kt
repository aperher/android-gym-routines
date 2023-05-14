package com.example.gymroutines.ui.home.routinesCatalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.R
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
            binding.tvTitle.text = routine.title
            binding.tvInfo.text = routine.information
            when(routine.imageURL){
                "gym1" -> binding.routineItemImg.setImageResource(R.drawable.gym1)
                "gym2" -> binding.routineItemImg.setImageResource(R.drawable.gym2)
                "gym3" -> binding.routineItemImg.setImageResource(R.drawable.gym3)
                else -> binding.routineItemImg.setImageResource(R.drawable.gym4)
            }
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