package com.rcacao.fintechchallenge.data.repository

import com.rcacao.fintechchallenge.data.model.ApiResponse
import com.rcacao.fintechchallenge.data.model.Contacts

interface ContactsRepository {
    suspend fun getContacts(): ApiResponse<List<Contacts>>
}