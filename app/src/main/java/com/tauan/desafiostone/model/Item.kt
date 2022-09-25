package com.tauan.desafiostone.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cart")
data class Item(
    @PrimaryKey
    var title: String,
    var quantity: Int = 0,
    var price: String,
    var seller: String,
    var thumbnailHd: String,
)
