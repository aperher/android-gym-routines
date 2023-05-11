package com.example.gymroutines.ui.Home.routinesCatalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymroutines.databinding.TextViewItemBinding

class ModalBottomSheetAdapter(
    private val items: List<String>,
    private val selectedValues: List<String>?,
    private val onClicked: (value: String) -> Unit
) : RecyclerView.Adapter<ModalBottomSheetAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TextViewItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: TextViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.tvItem.text = item

            binding.root.setOnClickListener {
                if (binding.ivChecked.visibility == View.VISIBLE)
                    binding.ivChecked.visibility = View.GONE
                else
                    binding.ivChecked.visibility = View.VISIBLE
                onClicked(item)
            }

            selectedValues?.let {
                if (it.contains(item))
                    binding.ivChecked.visibility = View.VISIBLE
            }
        }
    }
}