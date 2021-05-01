package com.coooldoggy.booksearch.ui.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.coooldoggy.booksearch.network.ApiManager
import com.coooldoggy.booksearch.network.data.BookSearchResponse
import com.coooldoggy.booksearch.ui.view.BookSearchResultAdapter
import kotlinx.coroutines.launch

class BookSearchViewModel(application: Application): AndroidViewModel(application) {
    private val TAG = BookSearchViewModel::class.java.simpleName

    private val _bookSearchList = MutableLiveData<BookSearchResponse>()
    val bookSearchList : LiveData<BookSearchResponse>
        get() = _bookSearchList

    val adapter: BookSearchResultAdapter = BookSearchResultAdapter()
    val searchText = MutableLiveData<String>()

    fun getSearchResult(header: String){
        viewModelScope.launch {
            val bookSearch = searchText.value
            if (bookSearch.isNullOrEmpty()){
                Toast.makeText(getApplication(), "검색어를 입력해 주세요!", Toast.LENGTH_SHORT).show()
                return@launch
            }

            adapter.clearData()

            kotlin.runCatching {
               ApiManager.queryBookTitle(header, bookSearch)?.let { response ->
                   if (response.isSuccessful){
                       val result = response.body()
                       Log.d(TAG, "$result")
                       result?.let { it ->
                           _bookSearchList.value = it
                       }
                   }else{
                       Log.d(TAG, "$response")
                   }
               }
            }
        }
    }
}