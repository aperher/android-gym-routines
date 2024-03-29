package com.example.gymroutines.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.gymroutines.R
import com.example.gymroutines.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = binding.navHostFragment.getFragment<NavHostFragment>().navController

        viewModel.goToHome.observe(this@MainActivity) { it ->
            it.getContentIfNotHandled()?.let {
                if (it) goToHome()
            }
        }
    }

    private fun goToHome() {
        navController.navigate(R.id.action_loginFragment_to_homeFragment)
    }
}