package com.example.gymroutines.ui.Home.profile.editProfile

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
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
    private val viewModel: EditProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
            Toast.makeText(context,"Save", Toast.LENGTH_SHORT).show()
        }
        //Set listener for cancelButton
        val cancelButton = view.findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            Toast.makeText(context,"Cancel", Toast.LENGTH_SHORT).show()
            navControllerHome?.popBackStack()
        }

        //Set text on textfields
        val usernameText = view.findViewById<TextInputEditText>(R.id.profile_name_text)
        //usernameText.setText(viewModel.user.value?.username)
        val useremailtext = view.findViewById<TextInputEditText>(R.id.email_text)
        //useremailtext.setText(viewModel.user.value?.email)
        initUI()
    }

    private fun initUI() {
        initObservers()
    }

    private fun initObservers(){
    }
}