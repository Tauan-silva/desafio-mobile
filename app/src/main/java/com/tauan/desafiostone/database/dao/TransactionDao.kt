package com.tauan.desafiostone.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tauan.desafiostone.model.Transaction

@Dao
interface TransactionDao {
    @Query("SELECT * FROM `transaction`")
    fun getAllCarts(): List<Transaction>

    @Query("SELECT * FROM `transaction` WHERE id IN (:id)")
    fun selectCartById(id: Long)

    @Insert
    fun insertAll(vararg transactions: Transaction)

    @Delete
    fun delete(transaction: Transaction)
}