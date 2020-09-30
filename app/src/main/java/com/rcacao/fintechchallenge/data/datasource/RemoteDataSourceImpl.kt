package com.rcacao.fintechchallenge.data.datasource

import com.rcacao.fintechchallenge.data.api.WebService
import com.rcacao.fintechchallenge.data.model.Contact
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: WebService) : RemoteDataSource {
    override suspend fun getContacts(): List<Contact> = api.contacts().contactsList
}