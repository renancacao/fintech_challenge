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

    private val mutableProgressVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val progressVisibility: LiveData<Boolean>
        get() = mutableProgressVisibility

    private val mutableListVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val listVisibility: LiveData<Boolean>
        get() = mutableListVisibility

    private val mutableButtonVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val buttonVisibility: LiveData<Boolean>
        get() = mutableButtonVisibility

    private val mutableErrorEvent: MutableLiveData<Event<String>> = MutableLiveData()
    val errorEvent: LiveData<Event<String>>
        get() = mutableErrorEvent

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
                    mutableErrorEvent.value = Event(result.errorMessage)
                }
            }
        }
    }

    private fun isLoading() {
        mutableButtonVisibility.value = false
        mutableProgressVisibility.value = true
    }

    private fun isLoaded() {
        mutableButtonVisibility.value = false
        mutableProgressVisibility.value = false
        mutableListVisibility.value = true
    }

    private fun isError() {
        mutableButtonVisibility.value = true
        mutableProgressVisibility.value = false
        mutableListVisibility.value = false
    }
}