package com.rcacao.fintechchallenge.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rcacao.fintechchallenge.data.api.WebService
import com.rcacao.fintechchallenge.data.model.ContactsList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class RemoteDataSourceImplTest {

    private val webService: WebService = mock()
    private val contactsList = ContactsList()

    @Test
    fun getContacts_returnContactsList() {
        runBlockingTest {
            whenever(webService.contacts()).thenReturn(contactsList)
            val dataSource: RemoteDataSource = RemoteDataSourceImpl(webService)
            assertEquals(contactsList.list, dataSource.getContacts())
        }
    }
}