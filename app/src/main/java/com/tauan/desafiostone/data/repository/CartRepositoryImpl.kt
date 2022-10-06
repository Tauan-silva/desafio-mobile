package com.tauan.desafiostone.data.repository

import com.tauan.desafiostone.data.database.dao.CartDao
import com.tauan.desafiostone.domain.model.Product
import com.tauan.desafiostone.domain.repository.CartRepository
import javax.inject.Inject


class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao
) : CartRepository {

    override suspend fun addToCart(product: Product) {
        cartDao.insert(product)
    }

    override suspend fun updateToCart(product: Product) {
        cartDao.update(product)
    }

    override suspend fun removeToCart(product: Product) {
        cartDao.delete(product)
    }

    override suspend fun getItemsFromCart(): List<Product> {
        return cartDao.getAllCarts()
    }

    override suspend fun getItemsCount(): Int {
        return cartDao.getQuantity()
    }
}