package com.example.gymroutines.ui.Home.routineDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.databinding.RoutineDetailEquipmentItemBinding
import com.example.gymroutines.model.Equipment

class RoutineDetailEquipmentAdapter :
    ListAdapter<Equipment, RoutineDetailEquipmentAdapter.ViewHolder>(EquipmentDetailDiff) {
    class ViewHolder(private val binding: RoutineDetailEquipmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(equipment: Equipment) {
            binding.chipEquipment.text = equipment.value
        }
    }

    object EquipmentDetailDiff : DiffUtil.ItemCallback<Equipment>() {
        override fun areContentsTheSame(oldItem: Equipment, newItem: Equipment): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(oldItem: Equipment, newItem: Equipment): Boolean = oldItem == newItem
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
