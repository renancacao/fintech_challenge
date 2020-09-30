package com.rcacao.fintechchallenge.data.repository

import com.rcacao.fintechchallenge.data.datasource.RemoteDataSource
import com.rcacao.fintechchallenge.data.model.ApiResponse
import com.rcacao.fintechchallenge.data.model.Contact
import javax.inject.Inject

class ContactsRepositoryImpl @Inject constructor(private val dataSource: RemoteDataSource) :
    ContactsRepository {

    override suspend fun getContacts(): ApiResponse<List<Contact>> = try {
        ApiResponse.Success(dataSource.getContacts())
    } catch (ex: Exception) {
        ApiResponse.Failure(ex)
    }

}