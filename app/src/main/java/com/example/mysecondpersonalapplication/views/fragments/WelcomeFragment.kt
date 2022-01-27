package com.example.mysecondpersonalapplication.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mysecondpersonalapplication.databinding.FragmentWelcomeBinding
import com.example.mysecondpersonalapplication.viewmodels.TodoViewModel

class WelcomeFragment: Fragment() {
    private var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding get() = _binding!!
    private lateinit var viewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[TodoViewModel::class.java]

        with(binding){
            continueBtn.setOnClickListener {
                val directions =
                    WelcomeFragmentDirections.actionWelcomeFragmentToToDoFragment()
                findNavController().navigate(directions)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}