package com.tauan.desafiostone.data.network

import com.tauan.desafiostone.data.network.dto.ProductDto
import retrofit2.http.GET

interface ApiService {
    @GET("stone-pagamentos/desafio-mobile/master/store/products.json")
    suspend fun getItems(): List<ProductDto>
}