package com.coooldoggy.booksearch.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.coooldoggy.booksearch.R
import com.coooldoggy.booksearch.databinding.ItemBookBinding
import com.coooldoggy.booksearch.databinding.NoItemLayoutBinding
import com.coooldoggy.booksearch.network.data.BookItem
import com.coooldoggy.booksearch.network.data.BookSearchResponse

class BookSearchResultAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var bookList = ArrayList<BookItem>()
    var itemClick: ItemClick? = null

    interface ItemClick {
        fun onClick(view: View, data: BookItem, position: Int)
    }

    fun setData(resultList: BookSearchResponse) {
        resultList.documents.forEach { documents ->
            bookList.add(BookItem(documents, false))
        }
        notifyDataSetChanged()
    }

    fun clearData() {
        bookList.clear()
        notifyDataSetChanged()
    }

    fun setFavorite(item: BookItem) {
        val index = bookList.indexOf(item)
        bookList[index] = item
        notifyItemChanged(index)
    }

    private fun getItem(position: Int): BookItem {
        return bookList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = DataBindingUtil.inflate<ItemBookBinding>(layoutInflater, R.layout.item_book, parent, false)
        return BookItemHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val bookItem = getItem(position)
        (holder as BookItemHolder).apply {
            bind(bookItem)
            itemView.setOnClickListener {
                itemClick?.onClick(it, bookItem, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

}