package com.tauan.desafiostone.presenter.viewmodel

import androidx.databinding.ObservableInt
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
    val itemQuantityLiveData =  MutableLiveData(0)

    fun addItemToCart(item: Item) {
        viewModelScope.launch {
            val response = repository.addToCart(item)
            itemQuantityLiveData.value = item.quantity
            addResponseLiveData.postValue(response)
        }
    }

    fun updateItemToCart(item: Item) {
        viewModelScope.launch {
            val response = repository.updateToCart(item)
            itemQuantityLiveData.value = item.quantity
            updateResponseLiveData.postValue(response)
        }
    }

    fun removeItemToCart(item: Item) {
        viewModelScope.launch {
            val response = repository.removeToCart(item)
            itemQuantityLiveData.value = 0
            removeResponseLiveData.postValue(response)
        }
    }
}