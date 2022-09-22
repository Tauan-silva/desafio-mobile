package com.tauan.desafiostone.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cart(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @Embedded val item: Item
)
