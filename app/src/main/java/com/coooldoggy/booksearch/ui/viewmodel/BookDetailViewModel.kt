package com.coooldoggy.booksearch.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.coooldoggy.booksearch.network.data.Documents

class BookDetailViewModel(application: Application): AndroidViewModel(application) {

    companion object{
        const val KEY_BOOK_ITEM = "KEY_BOOK_ITEM"
    }

    val bookItem = MutableLiveData<Documents>()
    val isFav: MutableLiveData<Boolean> = MutableLiveData(false)

    fun toggleIsFav(){
        isFav.value = !isFav.value!!
    }

}