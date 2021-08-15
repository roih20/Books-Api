package com.rodrigo.booksapi.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("volumes")
    suspend fun getBooks(@Query("q") query: String): BookListModel

}