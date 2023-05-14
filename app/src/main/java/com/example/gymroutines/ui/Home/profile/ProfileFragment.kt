package com.example.gymroutines.ui.Home.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var _navControllerHome: NavController? = null
    private val navControllerHome get() = _navControllerHome!!
    private val viewModel: ProfileViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)
        _navControllerHome = view.findNavController()
        val editProfileButton =
            binding.editProfileButton
        editProfileButton.setOnClickListener {
            navigateToEdit()
        }
        val closeButton = binding.closeButton
        closeButton.setOnClickListener {
            closeSession()
        }
        initUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _navControllerHome = null
    }

    private fun initUI() {
        initObservers()
    }

    private fun initObservers() {
        viewModel.user.observe(viewLifecycleOwner) {
            with(binding) {
                profileName.text = viewModel.user.value!!.username
                email.text = viewModel.user.value!!.email
                setImage()
            }
        }
    }

    private fun navigateToEdit() {
        navControllerHome.navigate(R.id.action_profileFragment_to_editProfileFragment)
    }

    private fun closeSession() {
        viewModel.closeSession()
        val navController = requireActivity().findNavController(R.id.navHostFragment)
        navController.navigate(R.id.action_homeFragment_to_loginFragment)
    }

    override fun onResume() {
        super.onResume()
        binding.profileName.text = viewModel.updatedUserName()
    }

    private fun setImage() {
        when (viewModel.user.value!!.imageUrl) {
            "perfil1" -> binding.profileImage.setImageResource(R.drawable.perfil1)
            "perfil2" -> binding.profileImage.setImageResource(R.drawable.perfil2)
            "perfil3" -> binding.profileImage.setImageResource(R.drawable.perfil3)
            else -> binding.profileImage.setImageResource(R.drawable.perfil4)
        }
    }
}