package com.rcacao.fintechchallenge.domain.model

import com.rcacao.fintechchallenge.data.model.Contacts

sealed class GetContactsResult {
    data class ContactsLoaded(val contacts: List<Contacts>) : GetContactsResult()
    data class Error(val errorMessage: String) : GetContactsResult()
}