package com.tauan.desafiostone.domain.use_case.cart

import com.tauan.desafiostone.domain.repository.CartRepository
import com.tauan.desafiostone.domain.use_case.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTotalValueFromCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) : UseCase<Unit, Int?> {

    override fun invoke(params: Unit): Flow<Int?> = flow {
        val totalValue = cartRepository.getTotalValueFromCart() ?: 0
        emit(totalValue)
    }

}