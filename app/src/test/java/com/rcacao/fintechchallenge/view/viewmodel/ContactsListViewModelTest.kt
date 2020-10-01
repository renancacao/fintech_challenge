package com.rcacao.fintechchallenge.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.rcacao.fintechchallenge.data.model.Contact
import com.rcacao.fintechchallenge.domain.model.GetContactsResult
import com.rcacao.fintechchallenge.domain.usecase.GetContactsUseCase
import com.rcacao.fintechchallenge.domain.usecase.SearchContactsUseCase
import com.rcacao.fintechchallenge.view.uistate.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ContactsListViewModelTest {

    private lateinit var viewModel: ContactsListViewModel
    private val getContactsUseCase: GetContactsUseCase = mock()
    private val searchContactsUseCase: SearchContactsUseCase = mock()

    private var observerVisibility: Observer<Boolean> = mock()
    private var observerContacts: Observer<List<Contact>> = mock()
    private var observerError: Observer<Event<String>> = mock()

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun loadContacts_OnSuccess_returnList() {
        runBlockingTest {
            val list: List<Contact> = listOf(Contact(), Contact())
            whenever(getContactsUseCase.invoke()).thenReturn(GetContactsResult.ContactsLoaded(list))

            viewModel = ContactsListViewModel(getContactsUseCase, searchContactsUseCase)
            viewModel.contacts.observeForever(observerContacts)

            verify(observerContacts).onChanged(list)
        }
    }

    @Test
    fun loadContacts_OnError_returnMessage() {
        runBlockingTest {
            whenever(getContactsUseCase.invoke()).thenReturn(GetContactsResult.Error("error message"))

            viewModel = ContactsListViewModel(getContactsUseCase, searchContactsUseCase)
            viewModel.error.observeForever(observerError)

            verify(observerError, times(1)).onChanged(any())
        }
    }

    @Test
    fun loadContacts_OnSuccess_listIsVisible() {
        runBlockingTest {
            whenever(getContactsUseCase.invoke()).thenReturn(GetContactsResult.ContactsLoaded(emptyList()))

            viewModel = ContactsListViewModel(getContactsUseCase, searchContactsUseCase)
            viewModel.isLoaded.observeForever(observerVisibility)

            verify(observerVisibility).onChanged(true)
        }
    }

    @Test
    fun loadContacts_OnSuccess_loadingIsInvisible() {
        runBlockingTest {
            whenever(getContactsUseCase.invoke()).thenReturn(GetContactsResult.ContactsLoaded(emptyList()))

            viewModel = ContactsListViewModel(getContactsUseCase, searchContactsUseCase)
            viewModel.isLoading.observeForever(observerVisibility)

            verify(observerVisibility).onChanged(false)
        }
    }

    @Test
    fun loadContacts_OnSuccess_buttonIsInvisible() {
        runBlockingTest {
            whenever(getContactsUseCase.invoke()).thenReturn(GetContactsResult.ContactsLoaded(emptyList()))

            viewModel = ContactsListViewModel(getContactsUseCase, searchContactsUseCase)
            viewModel.isError.observeForever(observerVisibility)

            verify(observerVisibility).onChanged(false)
        }
    }

    @Test
    fun loadContacts_OnError_listIsInvisible() {
        runBlockingTest {
            whenever(getContactsUseCase.invoke()).thenReturn(GetContactsResult.Error("error"))

            viewModel = ContactsListViewModel(getContactsUseCase, searchContactsUseCase)
            viewModel.isLoaded.observeForever(observerVisibility)

            verify(observerVisibility).onChanged(false)
        }
    }

    @Test
    fun loadContacts_OnError_loadingIsInvisible() {
        runBlockingTest {
            whenever(getContactsUseCase.invoke()).thenReturn(GetContactsResult.Error("error"))

            viewModel = ContactsListViewModel(getContactsUseCase, searchContactsUseCase)
            viewModel.isLoading.observeForever(observerVisibility)

            verify(observerVisibility).onChanged(false)
        }
    }

    @Test
    fun loadContacts_OnSuccess_buttonIsVisible() {
        runBlockingTest {
            whenever(getContactsUseCase.invoke()).thenReturn(GetContactsResult.Error("error"))

            viewModel = ContactsListViewModel(getContactsUseCase, searchContactsUseCase)
            viewModel.isError.observeForever(observerVisibility)

            verify(observerVisibility).onChanged(true)
        }
    }


}