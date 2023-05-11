package com.example.gymroutines.ui.Home.routineDetails

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.databinding.RoutineDetailEquipmentItemBinding

class RoutineDetailEquipmentAdapter(): ListAdapter<String, RoutineDetailEquipmentAdapter.ViewHolder>(EquipmentDetailDiff) {
    class ViewHolder(private val binding: RoutineDetailEquipmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(Equipment: String) {
            binding.tvEquipmentName.text = Equipment
            Log.d("Equipment:", Equipment)
        }
    }

    object
    EquipmentDetailDiff : DiffUtil.ItemCallback<String>() {
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem;
        }

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
          return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("CreateViewHolder:", "Equipment")
        return ViewHolder(
            RoutineDetailEquipmentItemBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        );
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(getItem(position)
       )
    }
}
