package com.tauan.desafiostone.presentation.ui.state

import com.tauan.desafiostone.domain.model.Product

data class ProductListState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val value: String = "",
    val error: String = ""
)
