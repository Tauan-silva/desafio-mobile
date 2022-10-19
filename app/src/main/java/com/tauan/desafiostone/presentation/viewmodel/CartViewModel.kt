package com.tauan.desafiostone.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tauan.desafiostone.common.CrudEnum
import com.tauan.desafiostone.common.Resource
import com.tauan.desafiostone.domain.model.Product
import com.tauan.desafiostone.domain.use_case.cart.CrudUseCase
import com.tauan.desafiostone.domain.use_case.cart.GetAllProductFromCartUseCase
import com.tauan.desafiostone.domain.use_case.cart.GetTotalValueFromCartUseCase
import com.tauan.desafiostone.presentation.ui.state.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getAllProductFromCartUseCase: GetAllProductFromCartUseCase,
    private val getTotalValueFromCartUseCase: GetTotalValueFromCartUseCase,
    private val crudUseCase: CrudUseCase,
) : ViewModel() {

    private val _allItemsFromCart = MutableLiveData<ProductListState>()
    private val _totalValueLiveData = MutableLiveData<Int?>()

    val allItemsFromCart: LiveData<ProductListState> = _allItemsFromCart
    val totalValueLiveData: LiveData<Int?> = _totalValueLiveData

    init {
        viewModelScope.launch {
            getItemsFromCart()
            getTotalValueFromCart()
        }
    }

    private suspend fun getItemsFromCart() {
        getAllProductFromCartUseCase(Unit).collect { result ->
            when (result) {
                is Resource.Loading -> {
                    _allItemsFromCart.value = ProductListState(isLoading = true)
                    Log.d("TAG", "getItemsFromCart: ${_allItemsFromCart.value}")
                }
                is Resource.Error -> {
                    _allItemsFromCart.value = ProductListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                    Log.d("TAG", "getItemsFromCart: ${_allItemsFromCart.value}")

                }
                is Resource.Success -> {
                    _allItemsFromCart.value = ProductListState(
                        products = result.data ?: emptyList()
                    )
                    Log.d("TAG", "getItemsFromCart: ${_allItemsFromCart.value}")

                }
            }
        }
    }

    private suspend fun getTotalValueFromCart() {
        getTotalValueFromCartUseCase(Unit).collect { value ->
            _totalValueLiveData.postValue(value)
        }
    }

    fun updateProductToCart(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            crudUseCase.crudMethod(CrudEnum.UPDATE)
            crudUseCase.invoke(product).collect { result ->
                withContext(Dispatchers.Main) {
                    when (result) {
                        is Resource.Success -> {
                            getTotalValueFromCartUseCase(Unit).collect {
                                _totalValueLiveData.postValue(it)
                            }
                        }
                        else -> {
                            Log.d(
                                "Update cart", "updateProductToCart: An unexpected error occurred"
                            )
                        }
                    }
                }
            }
        }
    }

    fun deleteProductToCart(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            crudUseCase.crudMethod(CrudEnum.DELETE)
            crudUseCase.invoke(product).collect { result ->
                withContext(Dispatchers.Main) {
                    when (result) {
                        is Resource.Success -> {
                            getTotalValueFromCartUseCase(Unit).collect {
                                _totalValueLiveData.postValue(it)
                            }
                        }
                        else -> {
                            Log.d(
                                "Update cart", "updateProductToCart: An unexpected error occurred"
                            )
                        }
                    }
                }
            }
        }
    }


}