package com.tauan.desafiostone.data.repository

import android.util.Log
import com.tauan.desafiostone.data.database.dao.CartDao
import com.tauan.desafiostone.model.Item
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class CartRepository @Inject constructor(private val cartDao: CartDao) {

    fun addToCart(item: Item): Boolean {
        var response = false
        CoroutineScope(Dispatchers.IO).launch {
            cartDao.insert(item)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    response = true
                }, { throwable ->
                    response = false
                    Log.e(this@CartRepository.javaClass.toString(), "addToCart: ", throwable.cause)
                })
        }

        return response
    }

    fun updateToCart(item: Item): Boolean {
        var response = false
        CoroutineScope(Dispatchers.IO).launch {
            cartDao.update(item)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    response = true
                }, { throwable ->
                    response = false
                    Log.e(this@CartRepository.javaClass.toString(), "updateToCart: ", throwable.cause)
                })
        }

        return response
    }

    fun removeToCart(item: Item): Boolean {
        var response = false
        CoroutineScope(Dispatchers.IO).launch {
            cartDao.delete(item)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    response = true
                }, { throwable ->
                    response = false
                    Log.e(this@CartRepository.javaClass.toString(), "removeToCart: ", throwable.cause)
                })
        }

        return response
    }
}