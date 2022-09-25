package com.tauan.desafiostone.presenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tauan.desafiostone.data.repository.CartRepository
import com.tauan.desafiostone.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {
    val addResponseLiveData = MutableLiveData(false)
    val updateResponseLiveData = MutableLiveData(false)
    val removeResponseLiveData = MutableLiveData(false)

    fun addItemToCart(item: Item) {
        viewModelScope.launch {
            val response = repository.addToCart(item)
            addResponseLiveData.postValue(response)
        }
    }
    fun updateItemToCart(item: Item) {
        viewModelScope.launch {
            val response = repository.addToCart(item)
            updateResponseLiveData.postValue(response)
        }
    }

    fun removeItemToCart(item: Item) {
        viewModelScope.launch {
            val response = repository.addToCart(item)
            removeResponseLiveData.postValue(response)
        }
    }
}