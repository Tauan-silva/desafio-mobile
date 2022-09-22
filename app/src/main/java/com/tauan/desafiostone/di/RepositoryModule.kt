package com.tauan.desafiostone.di

import com.tauan.desafiostone.network.ApiService
import com.tauan.desafiostone.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideItemRepository(apiService: ApiService) = ItemRepository(apiService)
}