package com.rcacao.fintechchallenge.data.repository

import com.nhaarman.mockitokotlin2.MockitoKotlinException
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rcacao.fintechchallenge.data.datasource.RemoteDataSource
import com.rcacao.fintechchallenge.data.model.ApiResponse
import com.rcacao.fintechchallenge.data.model.Contact
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class ContactsRepositoryImplTest {

    private lateinit var repository: ContactsRepository
    private val remoteDataSource: RemoteDataSource = mock()

    @Test
    fun getContacts_exception_returnFailure() {
        runBlockingTest {
            val ex = MockitoKotlinException("teste message", null)
            whenever(remoteDataSource.getContacts()).thenThrow(ex)
            repository = ContactsRepositoryImpl(remoteDataSource)

            val result: ApiResponse<List<Contact>> = repository.getContacts()

            assertTrue(result is ApiResponse.Failure)
            assertEquals(ex, (result as ApiResponse.Failure).exception)
        }
    }

    @Test
    fun getContacts_success_returnSuccess() {
        runBlockingTest {
            val list: List<Contact> = listOf(Contact(), Contact())
            whenever(remoteDataSource.getContacts()).thenReturn(list)
            repository = ContactsRepositoryImpl(remoteDataSource)

            val result: ApiResponse<List<Contact>> = repository.getContacts()

            assertTrue(result is ApiResponse.Success)
            assertEquals(list, (result as ApiResponse.Success).data)
        }
    }

    @Test
    fun getContacts_exception_returnSuccess() {
    }
}