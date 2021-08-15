package com.rodrigo.booksapi.repository

import com.rodrigo.booksapi.network.BookListModel
import com.rodrigo.booksapi.network.RetroInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class BookRepository(private val bookApi: RetroInstance) {

    suspend fun getBooksByName(query: String): BookListModel {
        return withContext(Dispatchers.IO){
            bookApi.service.getBooks(query)
        }
    }
}