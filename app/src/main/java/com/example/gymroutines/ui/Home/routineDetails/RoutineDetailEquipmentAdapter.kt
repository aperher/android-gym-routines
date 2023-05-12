package com.example.gymroutines.ui.Home.routineDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.databinding.RoutineDetailEquipmentItemBinding

class RoutineDetailEquipmentAdapter :
    ListAdapter<String, RoutineDetailEquipmentAdapter.ViewHolder>(EquipmentDetailDiff) {
    class ViewHolder(private val binding: RoutineDetailEquipmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(equipment: String) {
            binding.chipEquipment.text = equipment
        }
    }

    object
    EquipmentDetailDiff : DiffUtil.ItemCallback<String>() {
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RoutineDetailEquipmentItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
