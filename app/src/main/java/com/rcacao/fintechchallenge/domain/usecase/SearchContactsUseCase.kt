package com.rcacao.fintechchallenge.domain.usecase

import com.rcacao.fintechchallenge.data.model.Contact

interface SearchContactsUseCase {
    suspend operator fun invoke(text: String, list: List<Contact>): List<Contact>
}
