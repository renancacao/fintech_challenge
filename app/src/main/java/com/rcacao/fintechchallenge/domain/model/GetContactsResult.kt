package com.rcacao.fintechchallenge.domain.model

import com.rcacao.fintechchallenge.data.model.Contact

sealed class GetContactsResult {
    data class ContactsLoaded(val contacts: List<Contact>) : GetContactsResult()
    data class Error(val errorMessage: String) : GetContactsResult()
}