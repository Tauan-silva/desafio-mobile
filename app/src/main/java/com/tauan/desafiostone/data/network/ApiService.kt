package com.tauan.desafiostone.data.network

import com.tauan.desafiostone.model.Item
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("stone-pagamentos/desafio-mobile/master/store/products.json")
    fun getItems(): Call<List<Item>>
}