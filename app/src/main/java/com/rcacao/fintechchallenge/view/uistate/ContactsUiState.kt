package com.rcacao.fintechchallenge.view.uistate

import com.rcacao.fintechchallenge.data.model.Contacts

sealed class ContactsUiState {
    data class ContactsLoaded(val contacts: List<Contacts>) : ContactsUiState()
    data class Error(val errorMessage: String) : ContactsUiState()
    object Loading : ContactsUiState()
}
