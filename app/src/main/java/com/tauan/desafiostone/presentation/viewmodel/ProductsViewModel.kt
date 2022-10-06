package com.tauan.desafiostone.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tauan.desafiostone.common.CrudEnum
import com.tauan.desafiostone.common.Resource
import com.tauan.desafiostone.domain.model.Product
import com.tauan.desafiostone.domain.use_case.cart.CrudUseCase
import com.tauan.desafiostone.domain.use_case.cart.GetItemsCountUseCase
import com.tauan.desafiostone.domain.use_case.products.GetProductsUseCase
import com.tauan.desafiostone.presentation.ui.state.ProductListState
import com.tauan.desafiostone.presentation.ui.state.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val crudUseCase: CrudUseCase,
    private val getItemsCountUseCase: GetItemsCountUseCase
) : ViewModel() {

    private val _listState = MutableLiveData<ProductListState>()
    private val _productState = MutableLiveData<ProductState>()
    private val _itemCountState = MutableLiveData<Int>()

    val listState: LiveData<ProductListState> = _listState
    val productState: LiveData<ProductState> = _productState
    val itemCountState: LiveData<Int> = _itemCountState


    init {
        getProducts()
        getItemsCountFromCart()
    }

    private fun getProducts() {
        getProductsUseCase(Unit).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _listState.value = ProductListState(products = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _listState.value = ProductListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _listState.value = ProductListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addProductToCart(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            crudUseCase.crudMethod(CrudEnum.ADD)
            crudUseCase.invoke(product).collect { result ->
                withContext(Dispatchers.Main) {
                    when (result) {
                        is Resource.Success -> {
                            _productState.value = ProductState(isAdded = true)
                            getItemsCountFromCart()
                        }
                        else -> {
                            _productState.value = ProductState(isAdded = false, error = "An unexpected error occurred")
                        }
                    }
                }
            }
        }

    }

    fun updateProductToCart(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            crudUseCase.crudMethod(CrudEnum.UPDATE)
            crudUseCase.invoke(product).collect { result ->
                withContext(Dispatchers.Main) {
                    when (result) {
                        is Resource.Success -> {
                            _productState.value = ProductState(isAdded = true)
                            getItemsCountFromCart()
                        }
                        else -> {
                            _productState.value = ProductState(isAdded = false, error = "An unexpected error occurred")
                        }
                    }
                }
            }
        }
    }

    fun removeProductToCart(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            crudUseCase.crudMethod(CrudEnum.DELETE)
            crudUseCase.invoke(product).collect { result ->
                withContext(Dispatchers.Main) {
                    when (result) {
                        is Resource.Success -> {
                            _productState.value = ProductState(isAdded = false)
                            getItemsCountFromCart()
                        }
                        else -> {
                            _productState.value = ProductState(error = "An unexpected error occurred")
                        }
                    }
                }
            }
        }

    }

    private fun getItemsCountFromCart() {
        viewModelScope.launch(Dispatchers.IO) {
            getItemsCountUseCase(Unit).collect { count ->
                withContext(Dispatchers.Main) {
                    _itemCountState.value = count
                }
            }
        }
    }
}