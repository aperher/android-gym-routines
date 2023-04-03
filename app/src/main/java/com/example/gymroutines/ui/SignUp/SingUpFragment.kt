package com.example.gymroutines.ui.SignUp

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingUpFragment : Fragment(R.layout.fragment_sign_up) {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private var _navController: NavController? = null
    private val navController get() = _navController!!
    private val viewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignUpBinding.bind(view)
        _navController = view.findNavController()

        initUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _navController = null
    }

    private fun initUI() {
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.etName.addTextChangedListener(afterTextChanged = { text ->
            viewModel.setName(text.toString())
        })
        binding.etEmail.addTextChangedListener(afterTextChanged = { text ->
            viewModel.setEmail(text.toString())
        })
        binding.etPassword.addTextChangedListener(afterTextChanged = { text ->
            viewModel.setPassword(text.toString())
        })
        binding.etConfirmPassword.addTextChangedListener(afterTextChanged = { text ->
            viewModel.setConfirmPassword(text.toString())
        })
        binding.btnSignUp.setOnClickListener {
            viewModel.signUp()
        }
        binding.btnSignIn.setOnClickListener {
            navController.navigate(R.id.action_singUpFragment_to_loginFragment)
        }
    }

    private fun initObservers() {
        viewModel.invalidEmailError.observe(viewLifecycleOwner) {
            binding.tilEmail.error = it
        }
        viewModel.invalidPasswordError.observe(viewLifecycleOwner) {
            binding.tilPassword.error = it
        }
        viewModel.invalidConfirmPasswordError.observe(viewLifecycleOwner) {
            binding.tilConfirmPassword.error = it
        }
        viewModel.goToHome.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                goToHome()
            }
        }
    }

    private fun goToHome() {
        navController.navigate(R.id.action_singUpFragment_to_homeFragment)
    }
}