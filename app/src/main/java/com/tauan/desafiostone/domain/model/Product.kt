package com.tauan.desafiostone.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cart")
data class Product(
    @PrimaryKey
    var title: String,
    var quantity: Int,
    var price: Int,
    var seller: String,
    var thumbnailHd: String,
)
