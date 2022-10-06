package com.tauan.desafiostone.data.repository

import com.tauan.desafiostone.data.network.ApiService
import com.tauan.desafiostone.data.network.dto.ProductDto
import com.tauan.desafiostone.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ProductRepository {

    override suspend fun getProducts(): List<ProductDto> {
        return apiService.getItems()
    }
}