package com.tauan.desafiostone.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val value:Double,
    val date: Long,
    val fourLastDigitsCard: String,
    val purchaserName: String
)
