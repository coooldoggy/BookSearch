package com.coooldoggy.booksearch.ui.view

import androidx.recyclerview.widget.RecyclerView
import com.coooldoggy.booksearch.databinding.ItemBookBinding
import com.coooldoggy.booksearch.databinding.NoItemLayoutBinding
import com.coooldoggy.booksearch.network.data.BookItem

class BookItemHolder (private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(book: BookItem) {
        binding.model = book
    }
}