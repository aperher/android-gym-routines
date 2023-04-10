package com.example.gymroutines.ui.Home.routinesCatalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gymroutines.databinding.HeaderSearchItemBinding
import com.example.gymroutines.databinding.RoutineSliderItemBinding
import com.example.gymroutines.model.Catalog

class RoutinesCatalogAdapter(
    private val onRoutineClicked: (idRoutine: String) -> Unit
) : ListAdapter<Catalog, ViewHolder>(CatalogDiff) {
    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_ROUTINE = 1
    }

    object CatalogDiff : DiffUtil.ItemCallback<Catalog>() {
        override fun areItemsTheSame(oldItem: Catalog, newItem: Catalog): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Catalog, newItem: Catalog): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val binding: HeaderSearchItemBinding =
                    HeaderSearchItemBinding.inflate(inflater, parent, false)
                HeaderViewHolder(binding)
            }
            else -> {
                val binding: RoutineSliderItemBinding =
                    RoutineSliderItemBinding.inflate(inflater, parent, false)
                SliderItemViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_HEADER -> {
                val headerViewHolder = holder as HeaderViewHolder
                headerViewHolder.bind()
            }
            VIEW_TYPE_ROUTINE -> {
                val sliderViewHolder = holder as SliderItemViewHolder
                sliderViewHolder.bind(getItem(position))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_HEADER
            else -> VIEW_TYPE_ROUTINE
        }
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

    class HeaderViewHolder(private val binding: HeaderSearchItemBinding) :
        ViewHolder(binding.root) {
        fun bind() {
            // Se le podría pasar una lista de los filtros y meterlos en un recycler view
        }
    }

    override fun submitList(list: MutableList<Catalog>?) {
        list?.add(0, Catalog("", "", listOf())) // Header
        super.submitList(list)
    }
}