package com.coooldoggy.booksearch.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.coooldoggy.booksearch.R
import com.coooldoggy.booksearch.databinding.FragmentBookDetailBinding
import com.coooldoggy.booksearch.network.data.Documents
import com.coooldoggy.booksearch.ui.viewmodel.BookDetailViewModel

class BookDetailFragment: Fragment() {
    private val TAG = BookDetailFragment::class.java.simpleName
    private lateinit var dataBinding: FragmentBookDetailBinding
    private val viewModel by viewModels<BookDetailViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate<FragmentBookDetailBinding>(inflater, R.layout.fragment_book_detail, container, false).apply {
            model = viewModel
            lifecycleOwner = this@BookDetailFragment
        }
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            viewModel.bookItem.value = it.getSerializable(BookDetailViewModel.KEY_BOOK_ITEM) as? Documents
        }

        dataBinding.ivBack.setOnClickListener {
            view.findNavController().popBackStack()
        }

        viewModel.isFav.observe(viewLifecycleOwner, Observer {
            dataBinding.ivFavorite.isSelected = it
        })

        dataBinding.ivFavorite.setOnClickListener {
            viewModel.toggleIsFav()
        }
    }
}