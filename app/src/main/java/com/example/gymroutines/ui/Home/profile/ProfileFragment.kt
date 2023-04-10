package com.example.gymroutines.ui.Home.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentProfileBinding
import com.example.gymroutines.databinding.FragmentRoutinesBinding
import com.example.gymroutines.databinding.FragmentSignUpBinding
import com.example.gymroutines.ui.Home.routinesCatalog.RoutinesViewModel
import com.google.firebase.auth.ktx.userProfileChangeRequest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var _navControllerHome: NavController? = null
    private val navControllerHome get() = _navControllerHome!!
    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)
        _navControllerHome = view.findNavController()

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
        viewModel.user.observe(viewLifecycleOwner){
            with(binding){
                profileName.text = viewModel.user.value!!.username
                profileDescription.text = viewModel.user.value!!.email
            }
        }
    }
}