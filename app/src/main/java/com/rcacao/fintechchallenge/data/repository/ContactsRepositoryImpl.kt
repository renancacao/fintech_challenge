package com.rcacao.fintechchallenge.data.repository

import com.rcacao.fintechchallenge.data.datasource.RemoteDataSourceImpl
import com.rcacao.fintechchallenge.data.model.ApiResponse
import com.rcacao.fintechchallenge.data.model.Contact

class ContactsRepositoryImpl constructor(private val dataSource: RemoteDataSourceImpl) :
    ContactsRepository {

    override suspend fun getContacts(): ApiResponse<List<Contact>> = try {
        ApiResponse.Success(dataSource.getContacts())
    } catch (ex: Exception) {
        ApiResponse.Failure(ex)
    }

}