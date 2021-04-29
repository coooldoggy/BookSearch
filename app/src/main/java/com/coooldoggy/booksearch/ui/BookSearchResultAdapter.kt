package com.coooldoggy.booksearch.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.coooldoggy.booksearch.R
import com.coooldoggy.booksearch.databinding.ItemBookBinding

class BookSearchResultAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = DataBindingUtil.inflate<ItemBookBinding>(layoutInflater, R.layout.item_book, parent,false)
        return BookItemHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val book = bookList[position]
        (holder as BookItemHolder).apply {
//            bind(book)
            favoriteIcon.setOnClickListener {

            }
        }
    }

    override fun getItemCount(): Int {
//       return bookList.size
        return 0
    }

}