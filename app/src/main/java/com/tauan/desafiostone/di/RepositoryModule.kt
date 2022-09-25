package com.tauan.desafiostone.di

import com.tauan.desafiostone.data.database.dao.CartDao
import com.tauan.desafiostone.data.network.ApiService
import com.tauan.desafiostone.data.repository.CartRepository
import com.tauan.desafiostone.data.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideItemRepository(apiService: ApiService) = ItemRepository(apiService)

    @Provides
    fun provideCartRepository(cartDao: CartDao) = CartRepository(cartDao)
}