package com.tauan.desafiostone.presentation.ui.state

import com.tauan.desafiostone.domain.model.Product

data class ProductState(
    val isAdded: Boolean = false,
    val error: String = "",
    val product: Product? = null

)