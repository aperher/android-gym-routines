package com.example.gymroutines.ui.Home.chat


import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gymroutines.R
import com.example.gymroutines.databinding.FragmentChatBinding
import com.example.gymroutines.ui.Home.routinesCatalog.RoutinesCatalogAdapter
import com.example.gymroutines.utils.dismissKeyboard
import dagger.hilt.android.AndroidEntryPoint
import io.grpc.okhttp.internal.Platform.logger
import java.util.logging.Level
import kotlin.math.log

@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.fragment_chat) {
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
        initListeners()
    }

    private fun initObservers() {
        viewModel.listMessages.observe(viewLifecycleOwner) {
            adapter.submitList(it.toMutableList())
        }

        viewModel.isTextEmpty.observe(viewLifecycleOwner) {
            binding.btnSend.isEnabled = !it
        }
    }

    private fun initAdapter() {
        adapter = ChatAdapter()
        binding.chatRecyclerView.adapter = adapter
    }
    private fun initListeners() {
        binding.EtextMessage.addTextChangedListener(afterTextChanged = { text ->
            viewModel.setTextMessage(text.toString())
        })

        binding.btnSend.setOnClickListener {
           viewModel.createMessage()
            val text =""
            binding.EtextMessage.text = Editable.Factory.getInstance().newEditable("")
        }
    }


}
