package com.rcacao.fintechchallenge.domain.usecase

import com.rcacao.fintechchallenge.domain.model.GetContactsResult

interface GetContactsUseCase {
    suspend fun invoke(): GetContactsResult
}