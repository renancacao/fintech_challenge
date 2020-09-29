package com.rcacao.fintechchallenge.data.datasource

import com.rcacao.fintechchallenge.data.model.Contacts

interface RemoteDataSource {
    suspend fun getContacts(): List<Contacts>
}