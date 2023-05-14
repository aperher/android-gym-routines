package com.example.gymroutines.ui.home.routinesCatalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gymroutines.databinding.RoutineSliderItemBinding
import com.example.gymroutines.model.Catalog
import com.example.gymroutines.model.CatalogType

class RoutinesCatalogAdapter(
    private val onRoutineClicked: (idRoutine: String) -> Unit,
    private val onShowAllClicked: (idCatalog: CatalogType) -> Unit
) : ListAdapter<Catalog, RoutinesCatalogAdapter.SliderItemViewHolder>(CatalogDiff) {
    // Para que los recyclerviews internos de cada item utilicen siempre la misma viewPool
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RoutineSliderItemBinding.inflate(inflater, parent, false)
        binding.recycleViewRoutines.setRecycledViewPool(viewPool)
        return SliderItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SliderItemViewHolder(private val binding: RoutineSliderItemBinding) :
        ViewHolder(binding.root) {
        fun bind(slider: Catalog) {
            binding.tvSliderTitle.text = slider.title.value

            binding.tvShowAll.setOnClickListener {
                onShowAllClicked(slider.title)
            }

            val adapter = RoutinesSliderAdapter(onRoutineClicked)
            binding.recycleViewRoutines.adapter = adapter
            adapter.submitList(slider.routinesPreview)
        }
    }

    object CatalogDiff : DiffUtil.ItemCallback<Catalog>() {
        override fun areItemsTheSame(oldItem: Catalog, newItem: Catalog): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Catalog, newItem: Catalog): Boolean {
            return oldItem == newItem
        }
    }
}