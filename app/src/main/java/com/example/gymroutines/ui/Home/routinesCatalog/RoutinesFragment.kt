package com.example.gymroutines.ui.Home.routinesCatalog

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentRoutinesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoutinesFragment : Fragment(R.layout.fragment_routines) {
    private var _binding: FragmentRoutinesBinding? = null
    private val binding get() = _binding!!
    private var _navControllerHome: NavController? = null
    private val navControllerHome get() = _navControllerHome!!
    private val viewModel: RoutinesViewModel by viewModels()
    private lateinit var adapter: RoutinesCatalogAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRoutinesBinding.bind(view)
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

        viewModel.routinesCatalog.observe(viewLifecycleOwner) {
            Log.d("Catalogos", it.toString())
            adapter.submitList(it?.toMutableList())
        }
    }

    private fun goToRoutineDetails(routineId: String) {
        val bundle = bundleOf("id" to routineId)
        navControllerHome.navigate(R.id.action_routinesFragment_to_routineDetails, bundle)
    }

    private fun initAdapter() {
        /*var list = mutableListOf<SliderItem>(
            SliderItem(
                "Creadas por mi",
                listOf<SliderItem.RoutinePreview>(
                    SliderItem.RoutinePreview("id1", "Mi 6 pack", "url"),
                    SliderItem.RoutinePreview("id2", "Burning abs", "url"),
                    SliderItem.RoutinePreview("id3", "Mi 6 pack", "url"),
                    SliderItem.RoutinePreview("id4", "Burning abs", "url"),
                    SliderItem.RoutinePreview("id5", "Mi 6 pack", "url"),
                    SliderItem.RoutinePreview("id6", "Burning abs", "url")
                ),
            ),
            SliderItem(
                "Rutinas guardadas",
                listOf<SliderItem.RoutinePreview>(
                    SliderItem.RoutinePreview("id7", "Mi 6 pack", "url"),
                    SliderItem.RoutinePreview("id8", "Burning abs", "url"),
                    SliderItem.RoutinePreview("id9", "Mi 6 pack", "url"),
                    SliderItem.RoutinePreview("id10", "Burning abs", "url"),
                    SliderItem.RoutinePreview("id11", "Mi 6 pack", "url"),
                    SliderItem.RoutinePreview("id12", "Burning abs", "url")
                ),
            ),
            SliderItem(
                "Más populares",
                listOf<SliderItem.RoutinePreview>(
                    SliderItem.RoutinePreview("id13", "Mi 6 pack", "url"),
                    SliderItem.RoutinePreview("id14", "Burning abs", "url"),
                    SliderItem.RoutinePreview("id15", "Mi 6 pack", "url"),
                    SliderItem.RoutinePreview("id16", "Burning abs", "url"),
                    SliderItem.RoutinePreview("id17", "Mi 6 pack", "url"),
                    SliderItem.RoutinePreview("id18", "Burning abs", "url")
                ),
            ),
            SliderItem(
                "De la comunidad",
                listOf<SliderItem.RoutinePreview>(
                    SliderItem.RoutinePreview("id19", "Mi 6 pack", "url"),
                    SliderItem.RoutinePreview("id20", "Burning abs", "url"),
                    SliderItem.RoutinePreview("id21", "Mi 6 pack", "url"),
                    SliderItem.RoutinePreview("id22", "Burning abs", "url"),
                    SliderItem.RoutinePreview("id23", "Mi 6 pack", "url"),
                    SliderItem.RoutinePreview("id24", "Burning abs", "url")
                ),
            )
        )*/

        adapter = RoutinesCatalogAdapter { routineId ->
            viewModel.onRoutineClicked(routineId)
        }

        binding.recyclerView.adapter = adapter
    }
}