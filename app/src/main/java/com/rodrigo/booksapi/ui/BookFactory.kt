package com.rodrigo.booksapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rodrigo.booksapi.repository.BookRepository

class BookFactory(private val repository: BookRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)){
            return BookViewModel(repository) as T
        }
        throw Exception ("Unknown ViewModel class")
    }
}