package com.coooldoggy.booksearch.ui.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.coooldoggy.booksearch.R
import com.coooldoggy.booksearch.databinding.FragmentBookSearchBinding
import com.coooldoggy.booksearch.network.data.BookItem
import com.coooldoggy.booksearch.ui.viewmodel.BookDetailViewModel
import com.coooldoggy.booksearch.ui.viewmodel.BookSearchViewModel

class BookSearchFragment: Fragment() {
    private val TAG = BookSearchFragment::class.java.simpleName
    private lateinit var dataBinding: FragmentBookSearchBinding
    private val viewModel by viewModels<BookSearchViewModel>()
    private val detailViewModel by activityViewModels<BookDetailViewModel>()

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
                    hideSoftInputMethod(v)
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
        viewModel.adapter.itemClick = object : BookSearchResultAdapter.ItemClick{
            override fun onClick(view: View, data: BookItem, position: Int) {
                view.findNavController().navigate(R.id.action_go_to_detail_fragment, bundleOf(
                    BookDetailViewModel.KEY_BOOK_ITEM to data,
                    BookDetailViewModel.KEY_BOOK_ITEM_POSITION to position
                ))
            }
        }

        viewModel.bookSearchList.observe(viewLifecycleOwner, Observer{ item ->
            item?.let { resultList ->
                val adapter = dataBinding.rvBook.adapter
                (adapter as BookSearchResultAdapter).setData(resultList)
            }
        })

        detailViewModel.bookItem.observe(viewLifecycleOwner, Observer {
            val adapter = dataBinding.rvBook.adapter
            (adapter as BookSearchResultAdapter).setFavorite(it)
        })
    }

    private fun hideSoftInputMethod(view: View?) {
        kotlin.runCatching {
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.let {
                val targetView = view ?: activity?.currentFocus ?: return@let
                it.hideSoftInputFromWindow(targetView.windowToken, 0)
            }
        }
    }

}