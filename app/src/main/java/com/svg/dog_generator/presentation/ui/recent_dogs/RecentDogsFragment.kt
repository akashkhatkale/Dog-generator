package com.svg.dog_generator.presentation.ui.recent_dogs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.svg.dog_generator.R
import com.svg.dog_generator.common.data.Resource
import com.svg.dog_generator.common.utils.ToastUtils.showToast
import com.svg.dog_generator.databinding.FragmentRecentDogsBinding
import com.svg.dog_generator.presentation.adapters.RecentDogAdapter
import com.svg.dog_generator.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class RecentDogsFragment : Fragment() {

    @Inject
    lateinit var glide: RequestManager

    lateinit var binding: FragmentRecentDogsBinding
    lateinit var adapter: RecentDogAdapter

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapter = RecentDogAdapter(glide)
        binding = FragmentRecentDogsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpData()
        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        binding.clearButton.setOnClickListener {
            viewModel.clearAllDogs()
        }
    }

    private fun setUpData() {
        viewModel.fetchAllDogs()
        lifecycleScope.launchWhenStarted {
            viewModel.allDogs.collectLatest {
                when (it) {
                    is Resource.Success -> {
                        it.data?.let { data->
                            if (data.isNotEmpty()) {
                                adapter.differ.submitList(data)
                            } else {
                                adapter.differ.submitList(listOf())
                            }
                        }
                    }
                    is Resource.Error -> {
                        withContext(Dispatchers.Main){
                            requireContext().showToast(it.message?.message.orEmpty())
                        }
                    }
                    else -> Unit
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.clearDogs.collectLatest {
                when (it) {
                    is Resource.Success -> {
                        viewModel.fetchAllDogs()
                    }
                    is Resource.Error -> {
                        withContext(Dispatchers.Main){
                            requireContext().showToast(it.message?.message.orEmpty())
                        }
                    }
                    else -> Unit
                }
            }
        }

    }

    private fun setUpRecyclerView() {
        with(binding.recentDogsRecycler) {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = this@RecentDogsFragment.adapter
        }
    }
}