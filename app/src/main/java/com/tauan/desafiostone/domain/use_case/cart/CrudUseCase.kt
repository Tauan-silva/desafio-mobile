package com.tauan.desafiostone.domain.use_case.cart

import com.tauan.desafiostone.common.CrudEnum
import com.tauan.desafiostone.common.Resource
import com.tauan.desafiostone.domain.model.Product
import com.tauan.desafiostone.domain.repository.CartRepository
import com.tauan.desafiostone.domain.use_case.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class CrudUseCase @Inject constructor(
    private val repository: CartRepository
): UseCase<Product, Resource<Unit>>{

    private lateinit var crudMethod: CrudEnum

    override fun invoke(params: Product): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            val result = when(crudMethod) {
                CrudEnum.ADD -> repository.addToCart(params)
                CrudEnum.UPDATE -> repository.updateToCart(params)
                CrudEnum.DELETE -> repository.removeToCart(params)
            }
            emit(Resource.Success(result))
        }catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            emit(Resource.Success(Unit))
        }
    }

    fun crudMethod(crudEnum: CrudEnum){
        crudMethod = crudEnum
    }
}