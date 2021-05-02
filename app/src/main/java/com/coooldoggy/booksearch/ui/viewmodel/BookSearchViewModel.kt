package com.coooldoggy.booksearch.ui.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.coooldoggy.booksearch.R
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
    val noItemText = MutableLiveData<String>()
    private val toastMessage = MutableLiveData<ViewModelEvent<String>>()
    val message : LiveData<ViewModelEvent<String>>
        get() = toastMessage
    val isLoadMore = MutableLiveData<Boolean>(false)

    private var pageCount: Int = 1


    private fun plusCountPage(): Int{
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
                toastMessage.value = ViewModelEvent(getApplication<Application>().getString(R.string.search_input_require_text))
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
                           isLoadMore.value = false
                       }
                   }else{
                       showErrorPage(getApplication<Application>().getString(R.string.error_text))
                   }
               }
            }.onFailure {
                showErrorPage(getApplication<Application>().getString(R.string.error_text))
            }
        }
    }

    fun loadMore(header: String){
        viewModelScope.launch {
            val bookSearch = searchText.value ?: ""
            val meta = _bookSearchList.value?.meta ?: return@launch
            kotlin.runCatching {

                if(meta.isEnd || pageCount >= meta.pageableCount){
                    toastMessage.value = ViewModelEvent(getApplication<Application>().getString(R.string.alert_last_page))
                    return@launch
                }

                ApiManager.loadMoreBook(header, bookSearch, plusCountPage())?.let { response ->
                    if (response.isSuccessful){
                        val result = response.body()
                        noItemVisibility.postValue(View.GONE)
                        Log.d(TAG, "$result")
                        result?.let { it ->
                            _bookSearchList.value = it
                            isLoadMore.value = true
                        }
                    }else{
                        showErrorPage(getApplication<Application>().getString(R.string.error_text))
                    }
                }
            }.onFailure {
                showErrorPage(getApplication<Application>().getString(R.string.error_text))
            }
        }
    }

    private fun showErrorPage(errorMsg: String){
        noItemVisibility.postValue(View.VISIBLE)
        noItemText.value = errorMsg
    }
}