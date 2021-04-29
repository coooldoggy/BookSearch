package com.coooldoggy.booksearch.ui

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.coooldoggy.booksearch.databinding.ItemBookBinding
import com.coooldoggy.booksearch.network.data.Documents

class BookItemHolder (private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
    var favoriteIcon: ImageView = binding.ivFavorite

    fun bind(book: Documents) {
        binding.model = book
    }
}