package com.rcacao.fintechchallenge.data.model

import com.google.gson.annotations.SerializedName

class ContactsList {

    @SerializedName("data")
    val list: List<Contact> = emptyList()

}