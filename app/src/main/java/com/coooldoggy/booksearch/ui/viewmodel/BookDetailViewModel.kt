package com.coooldoggy.booksearch.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.coooldoggy.booksearch.network.data.BookItem

class BookDetailViewModel(application: Application): AndroidViewModel(application) {
    private val TAG = BookDetailViewModel::class.java.simpleName

    companion object{
        const val KEY_BOOK_ITEM = "KEY_BOOK_ITEM"
        const val KEY_BOOK_ITEM_POSITION = "KEY_BOOK_ITEM_POSITION"
    }

    val bookItem = MutableLiveData<BookItem>()
    val position = MutableLiveData<Int>()

    fun toggleIsFav(){
        bookItem.value?.let {
            it.isFavorite = !it.isFavorite
            bookItem.postValue(it)
        }
    }

}