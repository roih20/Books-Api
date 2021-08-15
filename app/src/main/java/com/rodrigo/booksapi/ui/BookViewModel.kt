package com.rodrigo.booksapi.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigo.booksapi.network.BookListModel
import com.rodrigo.booksapi.repository.BookRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class BookViewModel(private val repository: BookRepository): ViewModel() {

     var bookListModel= MutableLiveData<BookListModel>()

     fun getBooks(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.getBooksByName(query)
                bookListModel.value = response
            }catch (e: HttpException) {
                Log.d("Error", e.message())
            }
        }

    }


}