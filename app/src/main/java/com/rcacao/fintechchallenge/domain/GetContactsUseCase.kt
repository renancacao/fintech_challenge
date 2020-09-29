package com.rcacao.fintechchallenge.domain

import com.rcacao.fintechchallenge.view.uistate.ContactsUiState

interface GetContactsUseCase {
    suspend fun invoke(): ContactsUiState
}