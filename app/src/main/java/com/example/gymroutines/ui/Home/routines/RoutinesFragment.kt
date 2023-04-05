package com.example.gymroutines.ui.Home.routines

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentRoutinesBinding
import com.example.gymroutines.model.SliderItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoutinesFragment : Fragment(R.layout.fragment_routines) {
    private var _binding: FragmentRoutinesBinding? = null
    private val binding get() = _binding!!
    private var _navControllerHome: NavController? = null
    private val navControllerHome get() = _navControllerHome!!
    private val viewModel: RoutinesViewModel by viewModels()

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
    }

    private fun goToRoutineDetails(routineId: String) {
        val bundle = bundleOf("id" to routineId)
        navControllerHome.navigate(R.id.action_routinesFragment_to_routineDetails, bundle)
    }

    private fun initAdapter() {
        var list = mutableListOf<SliderItem>(
                SliderItem(
                    "id", "Creadas por mi",
                    listOf<SliderItem.RoutineItem>(
                        SliderItem.RoutineItem("id1", "Mi 6 pack", "url"),
                        SliderItem.RoutineItem("id2", "Burning abs", "url"),
                        SliderItem.RoutineItem("id3", "Mi 6 pack", "url"),
                        SliderItem.RoutineItem("id4", "Burning abs", "url"),
                        SliderItem.RoutineItem("id5", "Mi 6 pack", "url"),
                        SliderItem.RoutineItem("id6", "Burning abs", "url")
                    ),
                ),
                SliderItem(
                    "id", "Rutinas guardadas",
                    listOf<SliderItem.RoutineItem>(
                        SliderItem.RoutineItem("id7", "Mi 6 pack", "url"),
                        SliderItem.RoutineItem("id8", "Burning abs", "url"),
                        SliderItem.RoutineItem("id9", "Mi 6 pack", "url"),
                        SliderItem.RoutineItem("id10", "Burning abs", "url"),
                        SliderItem.RoutineItem("id11", "Mi 6 pack", "url"),
                        SliderItem.RoutineItem("id12", "Burning abs", "url")
                    ),
                ),
                SliderItem(
                    "id", "Más populares",
                    listOf<SliderItem.RoutineItem>(
                        SliderItem.RoutineItem("id13", "Mi 6 pack", "url"),
                        SliderItem.RoutineItem("id14", "Burning abs", "url"),
                        SliderItem.RoutineItem("id15", "Mi 6 pack", "url"),
                        SliderItem.RoutineItem("id16", "Burning abs", "url"),
                        SliderItem.RoutineItem("id17", "Mi 6 pack", "url"),
                        SliderItem.RoutineItem("id18", "Burning abs", "url")
                    ),
                ),
                SliderItem(
                    "id", "De la comunidad",
                    listOf<SliderItem.RoutineItem>(
                        SliderItem.RoutineItem("id19", "Mi 6 pack", "url"),
                        SliderItem.RoutineItem("id20", "Burning abs", "url"),
                        SliderItem.RoutineItem("id21", "Mi 6 pack", "url"),
                        SliderItem.RoutineItem("id22", "Burning abs", "url"),
                        SliderItem.RoutineItem("id23", "Mi 6 pack", "url"),
                        SliderItem.RoutineItem("id24", "Burning abs", "url")
                    ),
                )
            )

        val adapter = RoutinesCatalogAdapter(list) { routineId ->
            viewModel.onRoutineClicked(routineId)
        }

        binding.recyclerView.adapter = adapter

        list = mutableListOf(SliderItem(
            "id", "De la comunidad",
            listOf<SliderItem.RoutineItem>(
                SliderItem.RoutineItem("id19", "Mi 6 pack", "url"),
                SliderItem.RoutineItem("id20", "Burning abs", "url"),
                SliderItem.RoutineItem("id21", "Mi 6 pack", "url"),
                SliderItem.RoutineItem("id22", "Burning abs", "url"),
                SliderItem.RoutineItem("id23", "Mi 6 pack", "url"),
                SliderItem.RoutineItem("id24", "Burning abs", "url")
            ),
        ))

        adapter.notifyDataSetChanged()
    }
}