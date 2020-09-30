package com.rcacao.fintechchallenge.view.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rcacao.fintechchallenge.data.model.Contact
import com.rcacao.fintechchallenge.databinding.ItemContactBinding
import com.rcacao.fintechchallenge.view.ui.ContactsAdapter.ContactsViewHolder

class ContactsAdapter : RecyclerView.Adapter<ContactsViewHolder>() {

    var contacts: List<Contact> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val binding: ItemContactBinding =
            ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactsViewHolder(binding)
    }

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.binding.contact = contacts[position]
    }

    class ContactsViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root)

}