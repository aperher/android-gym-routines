package com.example.gymroutines.ui.Home.profile

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentProfileBinding
import com.example.gymroutines.ui.Home.profile.editProfile.EditProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var _navControllerHome: NavController? = null
    private val navControllerHome get() = _navControllerHome!!
    private var _navControllerMain: NavController? = null
    private val navControllerMain get() = _navControllerMain!!
    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)
        _navControllerHome = view.findNavController()
        val editProfilebutton = view.findViewById<Button>(R.id.edit_profile_button)
        editProfilebutton.setOnClickListener{
            //Navigate to edit
            navigateToEdit()
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
        viewModel.user.observe(viewLifecycleOwner){
            with(binding){
                profileName.text = viewModel.user.value!!.username
                email.text = viewModel.user.value!!.email
            }
        }
    }

    private fun navigateToEdit(){
        val activityContext = requireActivity()
        val context = activityContext
        Toast.makeText(context,"Editar perfil", Toast.LENGTH_SHORT).show()

        navControllerHome.navigate(R.id.action_profileFragment_to_editProfileFragment)
    }
}