package com.tauan.desafiostone.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tauan.desafiostone.data.database.dao.CartDao
import com.tauan.desafiostone.data.database.dao.TransactionDao
import com.tauan.desafiostone.domain.model.Product
import com.tauan.desafiostone.domain.model.Transaction
import javax.inject.Singleton

@Singleton
@Database(entities = [Product::class, Transaction::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        private var instance: AppDatabase? = null
        private val roomCallback: Callback = object : Callback() {
        }

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "database-application"
                ).build()
            }

            return instance
        }
    }
}