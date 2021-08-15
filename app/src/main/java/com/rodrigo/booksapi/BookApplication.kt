package com.rodrigo.booksapi

import android.app.Application
import com.rodrigo.booksapi.network.RetroInstance
import com.rodrigo.booksapi.repository.BookRepository

class BookApplication: Application() {

    val repository by lazy {
        BookRepository(RetroInstance)
    }
}