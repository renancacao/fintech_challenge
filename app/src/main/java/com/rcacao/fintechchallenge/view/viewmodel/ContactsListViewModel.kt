package com.rcacao.fintechchallenge.view.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rcacao.fintechchallenge.data.model.Contact
import com.rcacao.fintechchallenge.domain.model.GetContactsResult
import com.rcacao.fintechchallenge.domain.usecase.GetContactsUseCase
import com.rcacao.fintechchallenge.domain.usecase.SearchContactsUseCase
import com.rcacao.fintechchallenge.view.uistate.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContactsListViewModel @ViewModelInject @Inject
constructor(
    private val getContactsUseCase: GetContactsUseCase,
    private val searchContactsUseCase: SearchContactsUseCase
) : ViewModel() {

    private val mutableAllContacts: MutableLiveData<List<Contact>> = MutableLiveData()
    private val mutableContacts: MutableLiveData<List<Contact>> = MutableLiveData()
    private val mutableSearchText: MutableLiveData<String> = MutableLiveData()
    private val mutableIsLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val mutableIsLoaded: MutableLiveData<Boolean> = MutableLiveData()
    private val mutableIsError: MutableLiveData<Boolean> = MutableLiveData()
    private val mutableError: MutableLiveData<Event<String>> = MutableLiveData()

    val contacts: LiveData<List<Contact>> get() = mutableContacts
    val searchText: LiveData<String> get() = mutableSearchText
    val isLoading: LiveData<Boolean> get() = mutableIsLoading
    val isLoaded: LiveData<Boolean> get() = mutableIsLoaded
    val isError: LiveData<Boolean> get() = mutableIsError
    val error: LiveData<Event<String>> get() = mutableError

    init {
        loadContacts()
    }

    fun loadContacts() {
        isLoading()
        viewModelScope.launch {
            when (val result: GetContactsResult = getContactsUseCase()) {
                is GetContactsResult.ContactsLoaded -> isLoaded(result.contacts)
                is GetContactsResult.Error -> isError(result.errorMessage)
            }
        }
    }

    fun searchContacts(text: String) {
        isLoading()
        mutableSearchText.value = text
        viewModelScope.launch {
            isSearched(searchContactsUseCase(text, mutableAllContacts.value ?: emptyList()))
        }
    }

    private fun isLoading() {
        mutableIsLoading.value = true
        mutableIsError.value = false
    }

    private fun isLoaded(contacts: List<Contact>) {
        setupLoadedVisibility()
        mutableSearchText.value = ""
        mutableAllContacts.value = contacts
        mutableContacts.value = contacts
    }

    private fun isSearched(contacts: List<Contact>) {
        setupLoadedVisibility()
        mutableContacts.value = contacts
    }

    private fun setupLoadedVisibility() {
        mutableIsError.value = false
        mutableIsLoading.value = false
        mutableIsLoaded.value = true
    }

    private fun isError(errorMessage: String) {
        mutableIsError.value = true
        mutableIsLoading.value = false
        mutableIsLoaded.value = false
        mutableError.value = Event(errorMessage)
    }
}
