package com.coooldoggy.booksearch.network

import android.util.Log
import com.coooldoggy.booksearch.network.data.BookSearchResponse
import retrofit2.Response

object ApiManager {
    private val TAG = ApiManager::class.java.simpleName
    private val bookSearchApiService by lazy { NetworkManager.createService(BookSearchApiService::class.java) }

    suspend fun queryBookTitle(header: String, query: String): Response<BookSearchResponse>{
        Log.d(TAG, "queryBookTitle query = $query")
        return bookSearchApiService.queryBookTitle(key= header, query = query)
    }

    suspend fun loadMoreBook(header: String, query: String, page: Int): Response<BookSearchResponse>{
        return bookSearchApiService.queryBookTitle(key = header, query= query, page = page)
    }

}