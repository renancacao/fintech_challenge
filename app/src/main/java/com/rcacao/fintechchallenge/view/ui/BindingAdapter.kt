package com.rcacao.fintechchallenge.view.ui

import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rcacao.fintechchallenge.data.model.Contact
import com.rcacao.fintechchallenge.view.GlideApp
import com.rcacao.fintechchallenge.view.uistate.ContactsUiState


@BindingAdapter("bind:imageUrl")
fun setImageUrl(view: ImageView, url: String) {
    GlideApp.with(view.context)
        .load(url)
        .circleCrop()
        .into(view)
}

@BindingAdapter("bind:contacts")
fun setContacts(recyclerView: RecyclerView, contacts: List<Contact>?) {
    recyclerView.adapter = ContactsAdapter().apply {
        this.contacts = contacts ?: emptyList()
        notifyDataSetChanged()
    }
}

@BindingAdapter("bind:stateVisibility")
fun setVisibility(progressBar: ProgressBar, uiState: ContactsUiState?) {
    when (uiState) {
        is ContactsUiState.Loading -> progressBar.isVisible = true
        else -> progressBar.isVisible = false
    }
}

@BindingAdapter("bind:stateVisibility")
fun setVisibility(recyclerView: RecyclerView, uiState: ContactsUiState?) {
    when (uiState) {
        is ContactsUiState.Loaded -> recyclerView.isVisible = true
        is ContactsUiState.Error -> recyclerView.isVisible = false
    }
}

@BindingAdapter("bind:stateVisibility")
fun setVisibility(button: Button, uiState: ContactsUiState?) {
    when (uiState) {
        is ContactsUiState.Error -> button.isVisible = true
        else -> button.isVisible = false
    }
}

