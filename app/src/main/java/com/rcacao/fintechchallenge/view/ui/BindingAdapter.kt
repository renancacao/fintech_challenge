package com.rcacao.fintechchallenge.view.ui

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.rcacao.fintechchallenge.data.model.Contact
import com.rcacao.fintechchallenge.view.GlideApp


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

@BindingAdapter("bind:isVisible")
fun setVisibility(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}

@BindingAdapter("bind:isVisible")
fun setVisibility(swipe: SwipeRefreshLayout, isVisible: Boolean) {
    swipe.isRefreshing = isVisible
}


