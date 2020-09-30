package com.rcacao.fintechchallenge.domain.usecase

import com.nhaarman.mockitokotlin2.MockitoKotlinException
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rcacao.fintechchallenge.data.model.ApiResponse
import com.rcacao.fintechchallenge.data.model.Contact
import com.rcacao.fintechchallenge.data.repository.ContactsRepository
import com.rcacao.fintechchallenge.domain.model.GetContactsResult
import com.rcacao.fintechchallenge.utils.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class GetContactsUseCaseImplTest {

    private lateinit var usecase: GetContactsUseCase
    private val repository: ContactsRepository = mock()

    @Test
    fun getContactUseCase_Success_ReturnContactsLoaded() {
        runBlockingTest {
            val list: List<Contact> = listOf(Contact(), Contact())
            whenever(repository.getContacts()).thenReturn(ApiResponse.Success(list))
            usecase = GetContactsUseCaseImpl(repository)

            val result: GetContactsResult = usecase()

            Assert.assertTrue(result is GetContactsResult.ContactsLoaded)
            Assert.assertEquals(list, (result as GetContactsResult.ContactsLoaded).contacts)
        }
    }

    @Test
    fun getContactUseCase_Failure_ReturnContactsError() {
        runBlockingTest {
            val ex = MockitoKotlinException("teste message", null)
            whenever(repository.getContacts()).thenReturn(ApiResponse.Failure(ex))
            usecase = GetContactsUseCaseImpl(repository)

            val result: GetContactsResult = usecase()

            Assert.assertTrue(result is GetContactsResult.Error)
            Assert.assertEquals("teste message", (result as GetContactsResult.Error).errorMessage)
        }
    }

    @Test
    fun getContactUseCase_Failure_withoutMessage_ReturnContactsErrorGeneric() {
        runBlockingTest {
            val ex = MockitoKotlinException(null, null)
            whenever(repository.getContacts()).thenReturn(ApiResponse.Failure(ex))
            usecase = GetContactsUseCaseImpl(repository)

            val result: GetContactsResult = usecase()

            Assert.assertTrue(result is GetContactsResult.Error)
            Assert.assertEquals(
                Constants.GENERIC_ERROR,
                (result as GetContactsResult.Error).errorMessage
            )
        }
    }
}