package com.tauan.desafiostone.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tauan.desafiostone.common.Resource
import com.tauan.desafiostone.domain.use_case.cart.GetAllProductFromCartUseCase
import com.tauan.desafiostone.presentation.ui.state.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getAllProductFromCartUseCase: GetAllProductFromCartUseCase
) : ViewModel() {

    private val _allItemsFromCart = MutableLiveData<ProductListState>()
    private val allItemsFromCart: LiveData<ProductListState> = _allItemsFromCart

    init {
        viewModelScope.launch {
            getItemsFromCart()

        }
    }

    private fun getItemsFromCart() {
        getAllProductFromCartUseCase(Unit).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _allItemsFromCart.value = ProductListState(isLoading = true)
                }
                is Resource.Error -> {
                    _allItemsFromCart.value = ProductListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Success -> {
                    _allItemsFromCart.value = ProductListState(
                        products = result.data ?: emptyList()
                    )
                }
            }
        }
    }

}