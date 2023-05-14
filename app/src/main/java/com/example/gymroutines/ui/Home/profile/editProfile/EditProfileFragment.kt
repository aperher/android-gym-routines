package com.example.gymroutines.ui.Home.profile.editProfile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentEditProfileBinding
import com.example.gymroutines.ui.Home.profile.ProfileViewModel
import com.google.android.material.snackbar.Snackbar

class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private var _navControllerHome: NavController? = null
    private val navControllerHome get() = _navControllerHome
    private val viewModel: ProfileViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentEditProfileBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        _navControllerHome = view.findNavController()
        initUI()
    }

    private fun initUI() {
        initObservers()
        initListeners()
        setImage()
        binding.profileNameText.setText(viewModel.user.value?.username)
    }

    private fun initObservers() {
        viewModel.exception.observe(viewLifecycleOwner) { exception ->
            if (exception != null) {
                Snackbar.make(requireView(), exception.message.toString(), Snackbar.LENGTH_SHORT)
                    .show()
                viewModel.resetError()
            }
        }
    }

    private fun initListeners() {
        val editProfilePhotoButton =
            binding.editProfilePhotoButton//view.findViewById<FloatingActionButton>(R.id.editProfilePhotoButton)
        editProfilePhotoButton.setOnClickListener {
        }
        val saveButton = binding.saveButton//view.findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            saveChangedData()
        }
        val cancelButton = binding.cancelButton//view.findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            navControllerHome?.popBackStack()
        }
    }

    private fun saveChangedData() {
        var result = false
        result = viewModel.updateUserData(binding.profileNameText.text.toString())
        navControllerHome?.popBackStack()
        /*if(!result){
            Snackbar.make(requireView(),"Ha ocurrido un error",Snackbar.LENGTH_SHORT).show()
        }*/
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