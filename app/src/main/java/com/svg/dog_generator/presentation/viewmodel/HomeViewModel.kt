package com.svg.dog_generator.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.svg.dog_generator.common.data.Resource
import com.svg.dog_generator.domain.entities.DogResponseModel
import com.svg.dog_generator.domain.usecase.ClearAllDogsUseCase
import com.svg.dog_generator.domain.usecase.GetAllDogsUseCase
import com.svg.dog_generator.domain.usecase.GetRandomDogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRandomDogUseCase: GetRandomDogUseCase,
    private val getAllDogsUseCase: GetAllDogsUseCase,
    private val clearAllDogsUseCase: ClearAllDogsUseCase,
) : ViewModel() {

    private val _dogResult = MutableStateFlow<Resource<DogResponseModel>>(Resource.Idle())
    val dogResult = _dogResult

    private val _allDogs = MutableStateFlow<Resource<List<DogResponseModel>>>(Resource.Idle())
    val allDogs = _allDogs

    private val _clearDogs = MutableStateFlow<Resource<Boolean>>(Resource.Idle())
    val clearDogs = _clearDogs

    fun fetchRandomDog() = viewModelScope.launch {
        _dogResult.emit(Resource.Loading())
        val result = getRandomDogUseCase.execute()
        _dogResult.emit(result)
    }

    fun fetchAllDogs() = viewModelScope.launch {
        _allDogs.emit(Resource.Loading())
        val result = getAllDogsUseCase.execute()
        _allDogs.emit(result)
    }

    fun clearAllDogs() = viewModelScope.launch {
        _clearDogs.emit(Resource.Loading())
        _clearDogs.emit(clearAllDogsUseCase.execute())
    }
}