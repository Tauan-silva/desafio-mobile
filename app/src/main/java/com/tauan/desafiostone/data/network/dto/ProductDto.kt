package com.tauan.desafiostone.data.network.dto

import com.tauan.desafiostone.domain.model.Product

data class ProductDto(
    var date: String,
    var price: Int,
    var seller: String,
    var thumbnailHd: String,
    var title: String,
    var zipcode: String
)

fun ProductDto.toProduct(): Product {
    return Product(title, 0, price, seller, thumbnailHd)
}