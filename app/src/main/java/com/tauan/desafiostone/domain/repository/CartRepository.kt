package com.tauan.desafiostone.domain.repository

import com.tauan.desafiostone.domain.model.Product

interface CartRepository {
    suspend fun addToCart(product: Product)
    suspend fun updateToCart(product: Product)
    suspend fun removeToCart(product: Product)
    suspend fun getItemsFromCart(): List<Product>
    suspend fun getItemsCount(): Int
}