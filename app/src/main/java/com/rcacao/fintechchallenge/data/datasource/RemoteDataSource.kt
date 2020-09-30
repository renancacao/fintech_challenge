package com.rcacao.fintechchallenge.data.datasource

import com.rcacao.fintechchallenge.data.model.Contact

interface RemoteDataSource {
    suspend fun getContacts(): List<Contact>
}