package com.rcacao.fintechchallenge.data.repository

import com.rcacao.fintechchallenge.data.model.ApiResponse
import com.rcacao.fintechchallenge.data.model.Contact

interface ContactsRepository {
    suspend fun getContacts(): ApiResponse<List<Contact>>
}