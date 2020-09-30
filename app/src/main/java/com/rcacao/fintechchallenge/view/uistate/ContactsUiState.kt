package com.rcacao.fintechchallenge.view.uistate

sealed class ContactsUiState {
    object Loading : ContactsUiState()
    object Loaded : ContactsUiState()
    object Error : ContactsUiState()
}
