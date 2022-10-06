package com.tauan.desafiostone.presentation.ui.adapter

import com.tauan.desafiostone.domain.model.Product

interface AdapterClick {
    fun add(product: Product)
    fun remove(product: Product)
}