package com.coooldoggy.booksearch.ui.view

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.coooldoggy.booksearch.databinding.ItemBookBinding
import com.coooldoggy.booksearch.databinding.NoItemLayoutBinding
import com.coooldoggy.booksearch.network.data.Documents

class BookItemHolder (private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
    var favoriteIcon: ImageView = binding.ivFavorite

    fun bind(book: Documents) {
        binding.model = book
    }
}

class NoItemHolder(private val binding: NoItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {

}