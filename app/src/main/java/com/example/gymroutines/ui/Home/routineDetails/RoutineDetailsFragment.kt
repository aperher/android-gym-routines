package com.example.gymroutines.ui.Home.routineDetails

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentRoutineDetailsBinding
import com.example.gymroutines.model.StaticImages
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoutineDetailsFragment : Fragment(R.layout.fragment_routine_details),
    DeleteRoutineDialogFragment.DeleteInterface, MenuProvider {
    private var _binding: FragmentRoutineDetailsBinding? = null
    private val binding get() = _binding!!
    private var _navControllerHome: NavController? = null
    private val navControllerHome get() = _navControllerHome!!
    private lateinit var equipmentAdapter: RoutineDetailEquipmentAdapter
    private lateinit var exercisesAdapter: RoutineDetailExercisesAdapter
    private val viewModel: RoutineDetailsViewModel by viewModels()

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_routinedetails, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRoutineDetailsBinding.bind(view)
        _navControllerHome = view.findNavController()

        initUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _navControllerHome = null
    }

    private fun initUI() {
        initToolbar()
        initAdapters()
        initObservers()
        initListeners()
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            navControllerHome.navigateUp()
        }
    }

    private fun initAdapters() {
        equipmentAdapter = RoutineDetailEquipmentAdapter()
        binding.rvEquipment.adapter = equipmentAdapter
        exercisesAdapter = RoutineDetailExercisesAdapter()
        binding.rvExercises.adapter = exercisesAdapter
    }

    private fun initObservers() {
        viewModel.getRoutineId(arguments?.getString("id") ?: "")
            .observe(viewLifecycleOwner) { routineDetail ->
                binding.toolbar.title = routineDetail.title
                if (viewModel.isAuthor(routineDetail.userId)) binding.toolbar.addMenuProvider(
                    this@RoutineDetailsFragment,
                    viewLifecycleOwner,
                    Lifecycle.State.RESUMED
                )
                binding.tvRoutineLevel.text = routineDetail.level.value
                binding.tvDescription.text = routineDetail.description
                equipmentAdapter.submitList(routineDetail.equipment.toMutableList())
                exercisesAdapter.submitList(routineDetail.exercises.toMutableList())
                viewModel.isFavourite = routineDetail.isFavourite
                setFavouriteIconState(routineDetail.isFavourite)
                when (routineDetail.imageURL) {
                    StaticImages.IMAGE_ROWING.value -> binding.ivRoutineImage.setImageResource(R.drawable.gym1)
                    StaticImages.IMAGE_ABS.value -> binding.ivRoutineImage.setImageResource(R.drawable.gym2)
                    StaticImages.IMAGE_BICEPS.value -> binding.ivRoutineImage.setImageResource(R.drawable.gym3)
                    StaticImages.IMAGE_ROPES.value -> binding.ivRoutineImage.setImageResource(R.drawable.gym4)
                }
            }
    }

    private fun initListeners() {
        binding.fabFavorite.setOnClickListener {
            viewModel.onFavouriteClicked()

            viewModel.isFavourite?.let { isFavourite ->
                setFavouriteIconState(isFavourite)
            }
        }
    }

    private fun setFavouriteIconState(isFavourite: Boolean) {
        if (isFavourite) {
            binding.fabFavorite.setImageResource(R.drawable.favorite)
        } else {
            binding.fabFavorite.setImageResource(R.drawable.favorite_border)
        }
    }

    override fun positiveButton() {
        viewModel.deleteRoutine()
    }

    override fun negativeButton() {}

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean =
        when (menuItem.itemId) {
            R.id.deleteRoutine -> {
                DeleteRoutineDialogFragment(this).show(childFragmentManager, null); true
            }
            else -> false
        }
}