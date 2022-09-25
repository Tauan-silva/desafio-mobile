package com.tauan.desafiostone.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.tauan.desafiostone.model.Item
import io.reactivex.Completable

@Dao
interface CartDao {
    @Query("SELECT * FROM Cart")
    suspend fun getAllCarts(): List<Item>

    @Query("SELECT * FROM cart WHERE title IN (:title)")
    suspend fun selectCartById(title: String): Item

    @Insert(onConflict = REPLACE)
    fun insert(item: Item): Completable

    @Update
    fun update(item: Item): Completable

    @Delete
    fun delete(item: Item): Completable

    @Query("SELECT SUM(quantity) FROM Cart")
    fun getQuantity(): Int
}