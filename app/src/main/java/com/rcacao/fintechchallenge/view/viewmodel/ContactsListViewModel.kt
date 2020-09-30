package com.rcacao.fintechchallenge.view.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rcacao.fintechchallenge.data.model.Contact
import com.rcacao.fintechchallenge.domain.model.GetContactsResult
import com.rcacao.fintechchallenge.domain.usecase.GetContactsUseCase
import com.rcacao.fintechchallenge.view.uistate.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContactsListViewModel @ViewModelInject @Inject
constructor(private val getContactsUseCase: GetContactsUseCase) : ViewModel() {

    private val mutableContacts: MutableLiveData<List<Contact>> = MutableLiveData()
    val contacts: LiveData<List<Contact>>
        get() = mutableContacts

    private val mutableLoadingVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val loadingVisibility: LiveData<Boolean>
        get() = mutableLoadingVisibility

    private val mutableListVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val listVisibility: LiveData<Boolean>
        get() = mutableListVisibility

    private val mutableButtonVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val buttonVisibility: LiveData<Boolean>
        get() = mutableButtonVisibility

    private val mutableError: MutableLiveData<Event<String>> = MutableLiveData()
    val error: LiveData<Event<String>>
        get() = mutableError

    init {
        loadContacts()
    }

    fun loadContacts() {
        isLoading()
        viewModelScope.launch {
            when (val result = getContactsUseCase()) {
                is GetContactsResult.ContactsLoaded -> {
                    isLoaded()
                    mutableContacts.value = result.contacts
                }
                is GetContactsResult.Error -> {
                    isError()
                    mutableError.value = Event(result.errorMessage)
                }
            }
        }
    }

    private fun isLoading() {
        mutableButtonVisibility.value = false
        mutableLoadingVisibility.value = true
    }

    private fun isLoaded() {
        mutableButtonVisibility.value = false
        mutableLoadingVisibility.value = false
        mutableListVisibility.value = true
    }

    private fun isError() {
        mutableButtonVisibility.value = true
        mutableLoadingVisibility.value = false
        mutableListVisibility.value = false
    }
}
