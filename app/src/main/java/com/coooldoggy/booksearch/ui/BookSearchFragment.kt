package com.coooldoggy.booksearch.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.coooldoggy.booksearch.R
import com.coooldoggy.booksearch.databinding.FragmentBookSearchBinding
import com.coooldoggy.booksearch.ui.viewmodel.BookSearchViewModel

class BookSearchFragment: Fragment() {
    private val TAG = BookSearchFragment::class.java.simpleName
    private lateinit var dataBinding: FragmentBookSearchBinding
    private val viewModel by viewModels<BookSearchViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate<FragmentBookSearchBinding>(inflater, R.layout.fragment_book_search, container, false).apply {
            model = viewModel
            lifecycleOwner = this@BookSearchFragment
        }
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setResources()
    }

    private fun setResources(){
        dataBinding.etSearch.apply {
            setOnEditorActionListener{ v, actionId, event ->
                if (EditorInfo.IME_ACTION_SEARCH == actionId) {
                    viewModel.getSearchResult(context.getString(R.string.kakao_app_key))
                    true
                } else {
                    false
                }
            }

            doAfterTextChanged {
                viewModel.searchText.value = it.toString()
            }
        }

        dataBinding.rvBook.layoutManager = LinearLayoutManager(context)

//        viewModel.bookSearchList.observe(viewLifecycleOwner, Observer{ item ->
//            item?.let { itItem ->
//                val adapter = dataBinding.rvBook.adapter
//                Log.d(TAG, "item = $itItem")
//            }
//        })
    }
}