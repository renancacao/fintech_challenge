package com.rcacao.fintechchallenge.domain.usecase

import com.rcacao.fintechchallenge.data.model.ApiResponse
import com.rcacao.fintechchallenge.data.model.Contacts
import com.rcacao.fintechchallenge.data.repository.ContactsRepository
import com.rcacao.fintechchallenge.domain.model.GetContactsResult
import com.rcacao.fintechchallenge.utils.Constants

class GetContactsUseCaseImpl constructor(private val repository: ContactsRepository) :
    GetContactsUseCase {

    override suspend operator fun invoke(): GetContactsResult {
        return when (val result: ApiResponse<List<Contacts>> = repository.getContacts()) {
            is ApiResponse.Success -> GetContactsResult.ContactsLoaded(result.data)
            is ApiResponse.Failure -> GetContactsResult.Error(
                result.exception.message
                    ?: Constants.GENERIC_ERROR
            )
        }
    }

}