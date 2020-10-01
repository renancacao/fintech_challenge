package com.rcacao.fintechchallenge.domain.usecase

import com.rcacao.fintechchallenge.data.model.Contact
import java.util.*
import javax.inject.Inject

class SearchContactsUseCaseImpl @Inject constructor() :
    SearchContactsUseCase {

    override suspend operator fun invoke(text: String, list: List<Contact>): List<Contact> =
        if (text.isEmpty()) {
            list
        } else {
            list.filter { toLower(it.name).startsWith(toLower(text)) }
        }

    private fun toLower(text: String): String = text.toLowerCase(Locale.ROOT)

}
