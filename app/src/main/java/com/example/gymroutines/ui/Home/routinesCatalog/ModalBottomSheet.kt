package com.example.gymroutines.ui.Home.routinesCatalog

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.BottomSheetBinding
import com.example.gymroutines.model.FilterType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ModalBottomSheet : BottomSheetDialogFragment(R.layout.bottom_sheet) {
    private var _binding: BottomSheetBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ModalBottomSheetViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = BottomSheetBinding.bind(view)

        initUI()
    }

    private fun initUI() {
        setDataPassedToViewModel()
        initObservers()
    }

    private fun setDataPassedToViewModel() {
        val filter: FilterType = arguments?.getSerializable("filter") as FilterType
        viewModel.setFilterType(filter)

        val selectedItem: String? = arguments?.getString("selectedItem")
        viewModel.setSelectedItem(selectedItem)
    }

    private fun initObservers() {
        viewModel.title.observe(viewLifecycleOwner) {
            binding.tvTitle.text = it
        }

        viewModel.list.observe(viewLifecycleOwner) { list ->
            val adapter = ModalBottomSheetAdapter(list, viewModel.selectedItem.value) {
                viewModel.onItemSelected(it)
            }
            binding.rvBottomSheet.adapter = adapter
        }

        viewModel.goToRoutinesFragment.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                goToRoutinesFragment()
            }
        }
    }

    private fun goToRoutinesFragment() {
        val bundle = bundleOf(viewModel.filterType.value.toString() to viewModel.selectedItem.value)
        findNavController().navigate(R.id.action_modalBottomSheet_to_routinesFragment, bundle)
    }
}