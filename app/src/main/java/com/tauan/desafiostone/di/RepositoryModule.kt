package com.tauan.desafiostone.di

import com.tauan.desafiostone.data.database.dao.CartDao
import com.tauan.desafiostone.data.network.ApiService
import com.tauan.desafiostone.data.repository.CartRepositoryImpl
import com.tauan.desafiostone.data.repository.ProductRepositoryImpl
import com.tauan.desafiostone.domain.repository.CartRepository
import com.tauan.desafiostone.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductRepository(apiService: ApiService): ProductRepository {
        return ProductRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCartRepository(cartDao: CartDao): CartRepository {
        return CartRepositoryImpl(cartDao)
    }
}