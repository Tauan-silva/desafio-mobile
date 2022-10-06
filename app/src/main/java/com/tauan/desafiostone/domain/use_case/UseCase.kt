package com.tauan.desafiostone.domain.use_case

import kotlinx.coroutines.flow.Flow

interface UseCase<P, R> {
    operator fun invoke(params: P): Flow<R>
}