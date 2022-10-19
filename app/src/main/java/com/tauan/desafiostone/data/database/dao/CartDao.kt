package com.tauan.desafiostone.data.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.tauan.desafiostone.domain.model.Product

@Dao
interface CartDao {
    @Query("SELECT * FROM Cart")
    suspend fun getAllCarts(): List<Product>

    @Query("SELECT * FROM cart WHERE title IN (:title)")
    suspend fun selectCartById(title: String): Product

    @Insert(onConflict = REPLACE)
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Delete
    fun delete(product: Product)

    @Query("SELECT SUM(quantity) FROM Cart")
    suspend fun getQuantity(): Int?

    @Query("SELECT SUM(quantity * price) as result FROM Cart")
    suspend fun getTotalValueFromCart(): Int?
}