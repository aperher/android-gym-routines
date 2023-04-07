package com.example.gymroutines.ui.Login

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentLoginBinding
import com.example.gymroutines.utils.dismissKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()
    private var _navController: NavController? = null
    private val navController get() = _navController!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)
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
        binding.etEmail.addTextChangedListener(afterTextChanged = { text ->
            viewModel.setEmail(text.toString())
        })
        binding.etPassword.addTextChangedListener(afterTextChanged = { text ->
            viewModel.setPassword(text.toString())
        })
        binding.btnSignIn.setOnClickListener {
            it.dismissKeyboard()
            viewModel.login()
        }
        binding.btnSignUp.setOnClickListener {
            goToSignUp()
        }
    }

    private fun initObservers() {
        viewModel.invalidEmailError.observe(viewLifecycleOwner) {
            binding.tilEmail.error = it
        }
        viewModel.invalidPasswordError.observe(viewLifecycleOwner) {
            binding.tilPassword.error = it
        }
        viewModel.goToHome.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                goToHome()
            }
        }
    }

    private fun goToSignUp() {
        navController.navigate(R.id.action_loginFragment_to_singUpFragment)
    }

    private fun goToHome() {
        navController.navigate(R.id.action_loginFragment_to_homeFragment)
    }
}