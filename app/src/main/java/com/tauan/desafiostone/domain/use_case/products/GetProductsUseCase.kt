package com.tauan.desafiostone.domain.use_case.products

import com.tauan.desafiostone.common.Resource
import com.tauan.desafiostone.data.network.dto.toProduct
import com.tauan.desafiostone.domain.model.Product
import com.tauan.desafiostone.domain.repository.ProductRepository
import com.tauan.desafiostone.domain.use_case.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
): UseCase<Unit, Resource<List<Product>>> {

    override operator fun invoke(params: Unit): Flow<Resource<List<Product>>> = flow {
        try {
            emit(Resource.Loading())
            val products = repository.getProducts().map { it.toProduct() }
            emit(Resource.Success(products))
        }catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}