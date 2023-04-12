package com.example.gymroutines.ui.Home.chat


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentChatBinding
import com.example.gymroutines.ui.Home.routinesCatalog.RoutinesCatalogAdapter

class ChatFragment: Fragment(R.layout.fragment_chat) {
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ChatViewModel by viewModels()
    private var _navControllerHome: NavController? = null
    private val navControllerHome get() = _navControllerHome!!
    private lateinit var adapter: ChatAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentChatBinding.bind(view)
        _navControllerHome = view.findNavController()
        initUI()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        _navControllerHome = null
    }

    private fun initUI() {
        initAdapter()
        initObservers()
    }

    private fun initObservers() {

    }

    private fun initAdapter() {

    }
}