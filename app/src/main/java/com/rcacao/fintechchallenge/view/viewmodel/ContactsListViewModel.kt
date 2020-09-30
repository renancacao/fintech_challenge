package com.rcacao.fintechchallenge.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rcacao.fintechchallenge.data.model.Contacts
import com.rcacao.fintechchallenge.domain.model.GetContactsResult
import com.rcacao.fintechchallenge.domain.usecase.GetContactsUseCaseImpl
import com.rcacao.fintechchallenge.view.uistate.ContactsUiState
import com.rcacao.fintechchallenge.view.uistate.Event
import kotlinx.coroutines.launch

class ContactsListViewModel constructor(private val getContactsUseCase: GetContactsUseCaseImpl) :
    ViewModel() {

    private val mutableContacts: MutableLiveData<List<Contacts>> = MutableLiveData()
    val contacts: LiveData<List<Contacts>>
        get() = mutableContacts

    private val mutableUIState: MutableLiveData<ContactsUiState> = MutableLiveData()
    val uiState: LiveData<ContactsUiState>
        get() = mutableUIState

    private val mutableErrorEvent: MutableLiveData<Event<String>> = MutableLiveData()
    val errorEvent: LiveData<Event<String>>
        get() = mutableErrorEvent

    init {
        loadContacts()
    }

    fun loadContacts() {
        mutableUIState.value = ContactsUiState.Loading
        viewModelScope.launch {
            when (val result = getContactsUseCase()) {
                is GetContactsResult.ContactsLoaded -> {
                    mutableContacts.value = result.contacts
                    mutableUIState.value = ContactsUiState.Loaded
                }
                is GetContactsResult.Error -> {
                    mutableErrorEvent.value = Event(result.errorMessage)
                    mutableUIState.value = ContactsUiState.Error
                }
            }
        }
    }
}