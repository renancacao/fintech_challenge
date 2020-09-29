package com.rcacao.fintechchallenge.data.datasource

import com.rcacao.fintechchallenge.data.api.WebService
import com.rcacao.fintechchallenge.data.model.Contacts

class RemoteDataSourceImpl(private val api: WebService) : RemoteDataSource {
    override suspend fun getContacts(): List<Contacts> = api.contacts()
}