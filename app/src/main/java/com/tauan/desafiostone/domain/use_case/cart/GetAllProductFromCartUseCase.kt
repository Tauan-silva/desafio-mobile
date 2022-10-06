package com.tauan.desafiostone.domain.use_case.cart

import com.tauan.desafiostone.common.Resource
import com.tauan.desafiostone.domain.model.Product
import com.tauan.desafiostone.domain.repository.CartRepository
import com.tauan.desafiostone.domain.use_case.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAllProductFromCartUseCase @Inject constructor(
    private val repository: CartRepository
) : UseCase<Unit, Resource<List<Product>>> {

    override fun invoke(params: Unit): Flow<Resource<List<Product>>> = flow {
        try {
            emit(Resource.Loading())
            val products = repository.getItemsFromCart()
            emit(Resource.Success(products))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}