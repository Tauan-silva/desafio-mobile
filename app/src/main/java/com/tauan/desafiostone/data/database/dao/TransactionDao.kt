package com.tauan.desafiostone.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tauan.desafiostone.model.Transaction
import io.reactivex.Completable

@Dao
interface TransactionDao {
    @Query("SELECT * FROM `transaction`")
    fun getAllCarts(): List<Transaction>

    @Query("SELECT * FROM `transaction` WHERE id IN (:id)")
    fun selectCartById(id: Long): Transaction

    @Insert
    fun insertAll(vararg transactions: Transaction): Completable

    @Delete
    fun delete(transaction: Transaction): Completable
}