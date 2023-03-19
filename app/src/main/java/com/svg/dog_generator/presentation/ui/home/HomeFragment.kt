package com.svg.dog_generator.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.svg.dog_generator.R
import com.svg.dog_generator.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        with(binding) {
            generateDogsButton.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_generateDogFragment)
            }
            recentDogsButton.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_recentDogsFragment)
            }
        }
    }

}