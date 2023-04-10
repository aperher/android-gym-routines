package com.example.gymroutines.ui.Home.routinesCatalog

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentCatalogRoutinesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoutinesFragment : Fragment(R.layout.fragment_catalog_routines) {
    private var _binding: FragmentCatalogRoutinesBinding? = null
    private val binding get() = _binding!!
    private var _navControllerHome: NavController? = null
    private val navControllerHome get() = _navControllerHome!!
    private val viewModel: RoutinesViewModel by viewModels()
    private lateinit var adapter: RoutinesCatalogAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCatalogRoutinesBinding.bind(view)
        _navControllerHome = view.findNavController()

        initUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _navControllerHome = null
    }

    private fun initUI() {
        initAdapter()
        initObservers()
    }

    private fun initObservers() {
        viewModel.goToRoutineDetails.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { routineId ->
                goToRoutineDetails(routineId)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.recyclerView.visibility = if (it) View.GONE else View.VISIBLE
            binding.recyclerViewLoading.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.routinesCatalog.observe(viewLifecycleOwner) {
            adapter.submitList(it.toMutableList())
        }
    }

    private fun goToRoutineDetails(routineId: String) {
        val bundle = bundleOf("id" to routineId)
        navControllerHome.navigate(R.id.action_routinesFragment_to_routineDetails, bundle)
    }

    private fun initAdapter() {
        adapter = RoutinesCatalogAdapter { routineId ->
            viewModel.onRoutineClicked(routineId)
        }

        binding.recyclerView.adapter = adapter
    }
}