package com.example.gymroutines.ui.Home.profile.editProfile

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentEditProfileBinding
import com.example.gymroutines.ui.Home.profile.ProfileViewModel
import androidx.core.widget.addTextChangedListener

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
        initUI()
    }

    private fun initUI() {
        initObservers()
        initListeners()
        setImage()

    }

    private fun initObservers(){

    }

    private fun initListeners(){
        val editProfilePhotoButton = binding.editProfilePhotoButton//view.findViewById<FloatingActionButton>(R.id.editProfilePhotoButton)
        editProfilePhotoButton.setOnClickListener{
            Toast.makeText(context,"Edit Photo", Toast.LENGTH_SHORT).show()
        }
        //Set listener for savebutton
        val saveButton = binding.saveButton//view.findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener{
            saveChangedData()
        }
        //Set listener for cancelButton
        val cancelButton = binding.cancelButton//view.findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            Toast.makeText(context,"Cancel", Toast.LENGTH_SHORT).show()
            navControllerHome?.popBackStack()
        }
    }
    private fun saveChangedData(){
        var result = false
        result = viewModel.updateUserData(binding.profileNameText.text.toString())
        navControllerHome?.popBackStack()
        if(result){
            Toast.makeText(context,"Actualizado con exito", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"Error en la actualizacion", Toast.LENGTH_SHORT).show()
        }
    }
    private fun setImage() {
        when(viewModel.user.value!!.imageUrl){
            "perfil1" -> binding.profileImage.setImageResource(R.drawable.perfil1)
            "perfil2" -> binding.profileImage.setImageResource(R.drawable.perfil2)
            "perfil3" -> binding.profileImage.setImageResource(R.drawable.perfil3)
            else -> binding.profileImage.setImageResource(R.drawable.perfil4)
        }
    }

}