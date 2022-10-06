package com.tauan.desafiostone.domain.repository

import com.tauan.desafiostone.data.network.dto.ProductDto

interface ProductRepository {

    suspend fun getProducts(): List<ProductDto>
}