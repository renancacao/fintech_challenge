package com.rcacao.fintechchallenge.domain

import com.rcacao.fintechchallenge.data.model.ApiResponse
import com.rcacao.fintechchallenge.data.model.Contacts
import com.rcacao.fintechchallenge.data.repository.ContactsRepository
import com.rcacao.fintechchallenge.utils.Constants
import com.rcacao.fintechchallenge.view.uistate.ContactsUiState

class GetContactsUseCaseImpl constructor(private val repository: ContactsRepository) : GetContactsUseCase {

    override suspend fun invoke(): ContactsUiState {
        return when (val result: ApiResponse<List<Contacts>> = repository.getContacts()) {
            is ApiResponse.Success -> ContactsUiState.ContactsLoaded(result.data)
            is ApiResponse.Failure -> ContactsUiState.Error(result.exception.message
                    ?: Constants.GENERIC_ERROR)
        }
    }

}