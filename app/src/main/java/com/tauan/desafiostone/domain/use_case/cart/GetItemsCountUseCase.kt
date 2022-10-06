package com.tauan.desafiostone.domain.use_case.cart

import com.tauan.desafiostone.domain.repository.CartRepository
import com.tauan.desafiostone.domain.use_case.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetItemsCountUseCase @Inject constructor(
    private val repository: CartRepository
): UseCase<Unit, Int> {

    override fun invoke(params: Unit): Flow<Int> = flow {
        val count = repository.getItemsCount()
        emit(count)
    }
}