package com.example.gymroutines.ui.Home.routines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gymroutines.databinding.HeaderSearchItemBinding
import com.example.gymroutines.databinding.RoutineSliderItemBinding
import com.example.gymroutines.model.SliderItem

class RoutinesCatalogAdapter(
    private val list: MutableList<SliderItem>,
    private val onRoutineClicked: (idRoutine: String) -> Unit
) : Adapter<ViewHolder>() {
    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_ROUTINE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            VIEW_TYPE_HEADER -> {
                val binding: HeaderSearchItemBinding =
                    HeaderSearchItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                HeaderViewHolder(binding)
            }
            else -> {
                val binding: RoutineSliderItemBinding =
                    RoutineSliderItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                SliderItemViewHolder(binding)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_HEADER -> {
                val headerViewHolder = holder as HeaderViewHolder
                headerViewHolder.bind()
            }
            VIEW_TYPE_ROUTINE -> {
                val routineViewHolder = holder as SliderItemViewHolder
                routineViewHolder.bind(list[position - 1])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_HEADER
            else -> VIEW_TYPE_ROUTINE
        }
    }

    override fun getItemCount(): Int = list.size + 1 // +1 por el header

    inner class SliderItemViewHolder(private val binding: RoutineSliderItemBinding) :
        ViewHolder(binding.root) {
        fun bind(slider: SliderItem) {
            binding.tvSliderTitle.text = slider.title
            val adapter = RoutinesSliderAdapter(onRoutineClicked)
            binding.recycleViewRoutines.adapter = adapter
            adapter.submitList(slider.routinesList)
        }
    }

    class HeaderViewHolder(private val binding: HeaderSearchItemBinding) :
        ViewHolder(binding.root) {
        fun bind() {
            // Se le podría pasar una lista de los filtros y meterlos en un recycler view
        }
    }
}