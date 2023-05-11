package com.example.gymroutines.ui.Home.routinesCatalog

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.gymroutines.R
import com.example.gymroutines.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ModalBottomSheet : BottomSheetDialogFragment(R.layout.bottom_sheet) {
    private var _binding: BottomSheetBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RoutinesViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = BottomSheetBinding.bind(view)

        initUI()
    }

    private fun initUI() {
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        viewModel.title.observe(viewLifecycleOwner) {
            binding.tvTitle.text = it
        }

        viewModel.list.observe(viewLifecycleOwner) { list ->
            val adapter = ModalBottomSheetAdapter(list, viewModel.selectedItems) { filter ->
                viewModel.addFilter(filter)
            }
            binding.rvBottomSheet.adapter = adapter
        }
    }

    private fun initListeners() {
        binding.ivClose.setOnClickListener {
            dismissNow()
        }
    }

    override fun dismiss() {
        super.dismiss()
        //Hacer llamada de filtrado
        Log.d("ModalBottomSheet", "dismiss")
    }
}