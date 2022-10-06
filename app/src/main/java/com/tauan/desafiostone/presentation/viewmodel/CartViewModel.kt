package com.tauan.desafiostone.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tauan.desafiostone.data.repository.CartRepositoryImpl
import com.tauan.desafiostone.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepositoryImpl
) : ViewModel() {
    val addResponseLiveData = MutableLiveData(false)
    val updateResponseLiveData = MutableLiveData(false)
    val removeResponseLiveData = MutableLiveData(false)
    val allItemsFromCart = MutableLiveData<List<Product>>()

    init {
        viewModelScope.launch {
            getItemsFromCart()

        }
    }

    fun addItemToCart(product: Product) {
        viewModelScope.launch {
            val response = repository.addToCart(product)
//            addResponseLiveData.postValue(response)
        }
    }

    fun updateItemToCart(product: Product) {
        viewModelScope.launch {
            val response = repository.updateToCart(product)
            //updateResponseLiveData.postValue(response)
        }
    }

    fun removeItemToCart(product: Product) {
        viewModelScope.launch {
            val response = repository.removeToCart(product)
            //removeResponseLiveData.postValue(response)
        }
    }

    private fun getItemsFromCart() {
//        repository.getItemsFromCart().collect {
//            allItemsFromCart.postValue(it)
//        }
    }

}