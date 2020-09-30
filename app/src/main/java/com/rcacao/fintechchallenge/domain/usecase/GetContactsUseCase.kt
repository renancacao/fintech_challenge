package com.rcacao.fintechchallenge.domain.usecase

import com.rcacao.fintechchallenge.domain.model.GetContactsResult

interface GetContactsUseCase {
    suspend operator fun invoke(): GetContactsResult
}