package com.tauan.desafiostone.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tauan.desafiostone.database.dao.CartDao
import com.tauan.desafiostone.database.dao.TransactionDao
import com.tauan.desafiostone.model.Cart
import com.tauan.desafiostone.model.Transaction

@Database(entities = [Cart::class, Transaction::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun transactionDao(): TransactionDao
}