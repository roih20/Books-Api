package com.rodrigo.booksapi.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rodrigo.booksapi.BookApplication
import com.rodrigo.booksapi.R
import com.rodrigo.booksapi.databinding.FragmentBookListBinding

class BookFragment: Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentBookListBinding ?= null
    private val binding get() = _binding!!

    private val factory by lazy {
        val app = requireActivity().application as BookApplication
        BookFactory(app.repository)
    }

    private val bookViewModel: BookViewModel by viewModels{
        factory
    }

    private val rvAdapter = BookAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = bookViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val rv = binding.recyclerViewBooks.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        bookViewModel.bookListModel.observe(viewLifecycleOwner) {
            rvAdapter.setData(it.items)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_book, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)


    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()){
            searchBooks(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
       return true
    }

    private fun searchBooks(query: String) {
        bookViewModel.getBooks(query)
    }
}