package com.rcacao.fintechchallenge.data.model

import com.google.gson.annotations.SerializedName

class ContactsList {

    @SerializedName("data")
    val contactsList: List<Contact> = emptyList()

}