package com.tauan.desafiostone.di

import com.tauan.desafiostone.common.Resource
import com.tauan.desafiostone.domain.model.Product
import com.tauan.desafiostone.domain.repository.CartRepository
import com.tauan.desafiostone.domain.repository.ProductRepository
import com.tauan.desafiostone.domain.use_case.UseCase
import com.tauan.desafiostone.domain.use_case.cart.CrudUseCase
import com.tauan.desafiostone.domain.use_case.cart.GetAllProductFromCartUseCase
import com.tauan.desafiostone.domain.use_case.cart.GetItemsCountUseCase
import com.tauan.desafiostone.domain.use_case.products.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetProductsUseCase(repository: ProductRepository): UseCase<Unit, Resource<List<Product>>> {
        return GetProductsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllProductsFromCartUseCase(repository: CartRepository): UseCase<Unit, Resource<List<Product>>> {
        return GetAllProductFromCartUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCrudUseCase(repository: CartRepository): UseCase<Product, Resource<Unit>> {
        return CrudUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetItemsCountUseCase(repository: CartRepository): UseCase<Unit, Int> {
        return GetItemsCountUseCase(repository)
    }

}