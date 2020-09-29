package com.rcacao.fintechchallenge.view.uistate

import com.rcacao.fintechchallenge.data.model.Contacts

sealed class ContactsUiState {
    object Loading : ContactsUiState()
    object Loaded : ContactsUiState()
    object Error : ContactsUiState()
}
