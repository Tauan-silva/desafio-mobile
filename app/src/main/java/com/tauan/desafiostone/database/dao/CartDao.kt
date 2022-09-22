package com.tauan.desafiostone.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tauan.desafiostone.model.Cart

@Dao
interface CartDao {
    @Query("SELECT * FROM cart")
    fun getAllCarts(): List<Cart>

    @Query("SELECT * FROM cart WHERE id IN (:id)")
    fun selectCartById(id: Long)

    @Insert
    fun insertAll(vararg carts: Cart)

    @Delete
    fun delete(cart: Cart)
}