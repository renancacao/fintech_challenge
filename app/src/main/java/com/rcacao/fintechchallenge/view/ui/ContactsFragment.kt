package com.rcacao.fintechchallenge.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.rcacao.fintechchallenge.R
import com.rcacao.fintechchallenge.view.viewmodel.ContactsListViewModel
import kotlinx.android.synthetic.main.fragment_contacts.*

class ContactsFragment : Fragment() {

    val viewModel: ContactsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerErrorEvent()
    }

    private fun observerErrorEvent() {
        viewModel.errorEvent.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { showError(it) }
        })
    }

    private fun showError(errorMessage: String) {
        Snackbar.make(frameLayout, errorMessage, Snackbar.LENGTH_LONG).show()
    }
}