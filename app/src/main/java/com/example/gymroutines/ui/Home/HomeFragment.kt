package com.example.gymroutines.ui.Home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentHomeBinding
import com.google.android.material.navigation.NavigationBarView

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var _navControllerHome: NavController? = null
    private val navControllerHome get() = _navControllerHome!!
    private val viewModel: HomeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        _navControllerHome = binding.navHostFragmentHome.getFragment<NavHostFragment>().navController
        (binding.bottomNavigationView as NavigationBarView).setupWithNavController(navControllerHome)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}