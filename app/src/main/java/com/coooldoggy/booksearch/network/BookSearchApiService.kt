package com.coooldoggy.booksearch.network

import retrofit2.http.GET
import retrofit2.http.Query

interface BookSearchApiService {

    @GET
    suspend fun queryBookTitle(@Query("target")target: String = "title", @Query("query")query: String)

}