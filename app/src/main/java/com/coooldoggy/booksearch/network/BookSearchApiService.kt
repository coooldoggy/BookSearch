package com.coooldoggy.booksearch.network

import com.coooldoggy.booksearch.SUB_BOOK_SEARCH_URL
import com.coooldoggy.booksearch.network.data.BookSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BookSearchApiService {

    @GET(SUB_BOOK_SEARCH_URL)
    suspend fun queryBookTitle(@Header("Authorization") key: String,
                               @Query("target")target: String = "title",
                               @Query("query")query: String,
                               @Query("page")page: Int = 1) : Response<BookSearchResponse>

}