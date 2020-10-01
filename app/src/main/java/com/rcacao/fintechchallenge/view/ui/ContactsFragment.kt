package com.rcacao.fintechchallenge.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.rcacao.fintechchallenge.R
import com.rcacao.fintechchallenge.databinding.FragmentContactsBinding
import com.rcacao.fintechchallenge.view.viewmodel.ContactsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private val viewModel: ContactsListViewModel by viewModels()
    private lateinit var binding: FragmentContactsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        setupSwipeRefresh()
        return binding.root
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener(this)
        binding.swipeRefresh.setColorSchemeResources(
            R.color.colorAccent,
            R.color.colorPrimary,
            R.color.colorPrimaryDark
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerErrorEvent()
    }

    private fun observerErrorEvent() {
        viewModel.error.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { showError(it) }
        })
    }

    private fun showError(errorMessage: String) {
        Snackbar.make(binding.frameLayout, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun onRefresh() {
        viewModel.loadContacts()
    }
}