package com.example.gymroutines.ui.Home.routinesCatalog

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentCatalogRoutinesBinding
import com.example.gymroutines.model.FilterType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoutinesFragment : Fragment(R.layout.fragment_catalog_routines) {
    private var _binding: FragmentCatalogRoutinesBinding? = null
    private val binding get() = _binding!!
    private var _navControllerHome: NavController? = null
    private val navControllerHome get() = _navControllerHome!!
    private val viewModel: RoutinesViewModel by activityViewModels()
    private lateinit var catalogAdapter: RoutinesCatalogAdapter
    private lateinit var routineListAdapter: RoutinesListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCatalogRoutinesBinding.bind(view)
        _navControllerHome = view.findNavController()

        onBackPressedHandler()
        initUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _navControllerHome = null
    }

    private fun initUI() {
        initAdapters()
        initObservers()
        initListeners()
    }

    private fun initAdapters() {
        catalogAdapter = RoutinesCatalogAdapter(
            onRoutineClicked = { routineId ->
                viewModel.onRoutineClicked(routineId)
            },
            onShowAllClicked = { catalogTitle ->
                viewModel.onShowAllClicked(catalogTitle)
            }
        )

        routineListAdapter = RoutinesListAdapter { routineId ->
            viewModel.onRoutineClicked(routineId)
        }
    }

    private fun initObservers() {
        viewModel.goToRoutineDetails.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { routineId ->
                goToRoutineDetails(routineId)
            }
        }

        viewModel.goToBottomSheetFilter.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                openBottomSheetDialog()
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.recyclerView.visibility = if (it) View.GONE else View.VISIBLE
            binding.recyclerViewLoading.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.routinesCatalog.observe(viewLifecycleOwner) {
            catalogAdapter.submitList(it.toMutableList())
        }

        viewModel.routinesList.observe(viewLifecycleOwner) { routineList ->
            if (routineList != null) {
                binding.ivArrowBack.visibility = View.VISIBLE
                binding.recyclerView.adapter = routineListAdapter
                routineListAdapter.submitList(routineList)
            } else {
                binding.ivArrowBack.visibility = View.GONE
                binding.recyclerView.adapter = catalogAdapter
                catalogAdapter.submitList(viewModel.routinesCatalog.value)
            }
        }
    }

    private fun initListeners() {
        binding.apply {
            chipMuscles.setOnClickListener {
                viewModel.openFilter(FilterType.Muscles)
            }
            chipEquipment.setOnClickListener {
                viewModel.openFilter(FilterType.Equipment)
            }
            chipLevel.setOnClickListener {
                viewModel.openFilter(FilterType.Level)
            }
        }

        binding.ivArrowBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun goToRoutineDetails(routineId: String) {
        val bundle = bundleOf("id" to routineId)
        navControllerHome.navigate(R.id.action_routinesFragment_to_routineDetails, bundle)
    }

    private fun openBottomSheetDialog() {
        navControllerHome.navigate(R.id.action_routinesFragment_to_modalBottomSheet)
    }

    private fun onBackPressedHandler() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (viewModel.routinesList.value != null) {
                        viewModel.showCatalog()
                    } else {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            })
    }
}