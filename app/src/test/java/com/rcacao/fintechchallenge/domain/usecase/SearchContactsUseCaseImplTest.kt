package com.rcacao.fintechchallenge.domain.usecase

import com.google.gson.Gson
import com.rcacao.fintechchallenge.data.model.Contact
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchContactsUseCaseImplTest {

    private lateinit var usecase: SearchContactsUseCase

    @Test
    fun searchContactUseCase_TextEmpty_ReturnList() {
        runBlockingTest {
            val list: List<Contact> = listOf(Contact(), Contact())

            usecase = SearchContactsUseCaseImpl()
            val result: List<Contact> = usecase("", list)

            Assert.assertEquals(list, result)
        }
    }

    @Test
    fun searchContactUseCase_NameExist_ReturnFilteredList() {
        runBlockingTest {
            val list: List<Contact> = mockList()

            usecase = SearchContactsUseCaseImpl()
            val result: List<Contact> = usecase("ma", list)

            Assert.assertEquals(2, result.size)
            Assert.assertEquals("Mayara", result[0].name)
            Assert.assertEquals("Maria", result[1].name)
        }
    }

    @Test
    fun searchContactUseCase_NameNotExist_ReturnEmptyList() {
        runBlockingTest {
            val list: List<Contact> = mockList()

            usecase = SearchContactsUseCaseImpl()
            val result: List<Contact> = usecase("lu", list)

            Assert.assertTrue(result.isEmpty())
        }
    }

    private fun mockList(): List<Contact> {
        val gson = Gson()
        val contact1: Contact = gson.fromJson("{\"name\":\"Renan\"}", Contact::class.java)
        val contact2: Contact = gson.fromJson("{\"name\":\"Mayara\"}", Contact::class.java)
        val contact3: Contact = gson.fromJson("{\"name\":\"Maria\"}", Contact::class.java)

        return listOf(contact1, contact2, contact3)
    }
}