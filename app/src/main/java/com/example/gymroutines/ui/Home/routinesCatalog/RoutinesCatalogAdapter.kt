package com.example.gymroutines.ui.Home.routinesCatalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gymroutines.databinding.RoutineSliderItemBinding
import com.example.gymroutines.model.Catalog

class RoutinesCatalogAdapter(
    private val onRoutineClicked: (idRoutine: String) -> Unit
) : ListAdapter<Catalog, RoutinesCatalogAdapter.SliderItemViewHolder>(CatalogDiff) {
    object CatalogDiff : DiffUtil.ItemCallback<Catalog>() {
        override fun areItemsTheSame(oldItem: Catalog, newItem: Catalog): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Catalog, newItem: Catalog): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RoutineSliderItemBinding =
            RoutineSliderItemBinding.inflate(inflater, parent, false)
        return SliderItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SliderItemViewHolder(private val binding: RoutineSliderItemBinding) :
        ViewHolder(binding.root) {
        fun bind(slider: Catalog) {
            binding.tvSliderTitle.text = slider.title

            val adapter = RoutinesSliderAdapter(onRoutineClicked)
            binding.recycleViewRoutines.adapter = adapter
            adapter.submitList(slider.routinesPreview)
        }
    }
}