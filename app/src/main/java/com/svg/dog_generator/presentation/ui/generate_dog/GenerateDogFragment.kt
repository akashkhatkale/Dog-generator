package com.svg.dog_generator.presentation.ui.generate_dog

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.bumptech.glide.RequestManager
import com.svg.dog_generator.R
import com.svg.dog_generator.common.data.Resource
import com.svg.dog_generator.databinding.FragmentGenerateDogBinding
import com.svg.dog_generator.domain.entities.DogResponseModel
import com.svg.dog_generator.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class GenerateDogFragment : Fragment() {

    @Inject
    lateinit var glide: RequestManager

    private lateinit var binding: FragmentGenerateDogBinding
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGenerateDogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClickListeners()
        listenToUi()
    }

    private fun listenToUi() {
        lifecycleScope.launchWhenStarted {
            viewModel.dogResult.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        setProgressVisibility(progress = true, enabled = false)
                    }
                    is Resource.Error -> {
                        setProgressVisibility(progress = false, enabled = true)
                        binding.errorMessage.text = it.message?.message.orEmpty()
                        binding.dogImage.isVisible = false
                        setErrorVisibility(eVisibility = true, dVisibility = false)
                    }
                    is Resource.Success -> {
                        setProgressVisibility(progress = false, enabled = true)
                        setDogImage(it.data)
                        setErrorVisibility(eVisibility = false, dVisibility = true)
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun setDogImage(data: DogResponseModel?) {
        data?.imageUrl?.let {
            binding.dogImage.load(it)
        }
    }

    private fun setErrorVisibility(eVisibility: Boolean, dVisibility: Boolean) {
        binding.errorMessage.isVisible = eVisibility
        binding.dogImage.isVisible = dVisibility
    }

    private fun setProgressVisibility(progress: Boolean, enabled: Boolean) {
        binding.generateDogProgressBar.isVisible = progress
        if (progress) {
            binding.generateButton.text = ""
        } else {
            binding.generateButton.setText(R.string.generate_string)
        }
        binding.generateButton.isEnabled = enabled
    }

    private fun setUpClickListeners() {
        binding.generateButton.setOnClickListener {
            viewModel.fetchRandomDog()
        }
    }

}