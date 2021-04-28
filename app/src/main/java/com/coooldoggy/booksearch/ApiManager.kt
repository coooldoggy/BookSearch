package com.coooldoggy.booksearch

import com.coooldoggy.booksearch.network.BookSearchApiService
import com.coooldoggy.booksearch.network.NetworkManager

object ApiManager {
    private val TAG = ApiManager::class.java.simpleName
    private val bookSearchApiService by lazy { NetworkManager.createService(BookSearchApiService::class.java) }

    suspend fun queryBookTitle(query: String){
        bookSearchApiService.queryBookTitle(query = query)
    }

}