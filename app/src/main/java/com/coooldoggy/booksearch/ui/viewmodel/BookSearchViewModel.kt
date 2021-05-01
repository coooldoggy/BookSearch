package com.coooldoggy.booksearch.ui.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
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
    val noItemVisibility = MutableLiveData<Int>(View.VISIBLE)
    private var pageCount: Int = 1

    fun plusCountPage(): Int{
        return ++pageCount
    }

    private fun clearData(){
        adapter.clearData()
        pageCount = 1
    }

    fun getSearchResult(header: String){
        viewModelScope.launch {
            val bookSearch = searchText.value
            if (bookSearch.isNullOrEmpty()){
                Toast.makeText(getApplication(), "검색어를 입력해 주세요!", Toast.LENGTH_SHORT).show()
                return@launch
            }

            clearData()

            kotlin.runCatching {
               ApiManager.queryBookTitle(header, bookSearch)?.let { response ->
                   if (response.isSuccessful){
                       val result = response.body()
                       noItemVisibility.postValue(View.GONE)
                       Log.d(TAG, "$result")
                       result?.let { it ->
                           _bookSearchList.value = it
                       }
                   }else{
                       noItemVisibility.postValue(View.VISIBLE)
                   }
               }
            }
        }
    }

    fun loadMore(header: String){
        viewModelScope.launch {
            val bookSearch = searchText.value ?: ""
            val meta = _bookSearchList.value?.meta ?: return@launch
            kotlin.runCatching {

                if(meta.isEnd || pageCount >= meta.pageableCount){
                    Toast.makeText(getApplication(), "마지막 페이지 입니다.", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                ApiManager.loadMoreBook(header, bookSearch, plusCountPage())?.let { response ->
                    if (response.isSuccessful){
                        val result = response.body()
                        noItemVisibility.postValue(View.GONE)
                        Log.d(TAG, "$result")
                        result?.let { it ->
                            _bookSearchList.value = it
                        }
                    }else{
                        noItemVisibility.postValue(View.VISIBLE)
                    }
                }
            }
        }
    }
}