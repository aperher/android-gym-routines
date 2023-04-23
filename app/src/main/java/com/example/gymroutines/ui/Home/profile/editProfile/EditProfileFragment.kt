package com.example.gymroutines.ui.Home.profile.editProfile

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentEditProfileBinding
import com.example.gymroutines.ui.Home.profile.ProfileViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class EditProfileFragment: Fragment(R.layout.fragment_edit_profile) {
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private var _navControllerHome: NavController? = null
    private val navControllerHome get() = _navControllerHome
    private val viewModel: ProfileViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentEditProfileBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        _navControllerHome = view.findNavController()
        //set listener for editProfilePhotoButton
        val editProfilePhotoButton = view.findViewById<FloatingActionButton>(R.id.editProfilePhotoButton)
        editProfilePhotoButton.setOnClickListener{
            Toast.makeText(context,"Edit Photo", Toast.LENGTH_SHORT).show()
        }
        //Set listener for savebutton
        val saveButton = view.findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener{
            saveChangedData()
        }
        //Set listener for cancelButton
        val cancelButton = view.findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            Toast.makeText(context,"Cancel", Toast.LENGTH_SHORT).show()
            navControllerHome?.popBackStack()
        }

        //Set text on textfields
        initUI()
    }

    private fun initUI() {
        binding.profileNameText.setText(viewModel.user.value?.username)
        binding.actualPasswordText.setText(null)
        binding.newPasswordText.setText(null)
        binding.repeatPasswordText.setText(null)
    }

    private fun saveChangedData(){
        Toast.makeText(context,binding.profileNameText.text, Toast.LENGTH_SHORT).show()
        viewModel.updateUserData(binding.profileNameText.text.toString(), binding.actualPasswordText.text.toString(),binding.newPasswordText.text.toString(),binding.repeatPasswordText.text.toString())
    }
}