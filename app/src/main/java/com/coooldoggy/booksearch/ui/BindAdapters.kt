package com.coooldoggy.booksearch.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coooldoggy.booksearch.R
import java.text.SimpleDateFormat
import java.util.*

object BindAdapters {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url: String) {
        Glide.with(view.context).load(url)
            .centerCrop()
            .placeholder(R.drawable.ic_no_image)
            .into(view)
    }

    @BindingAdapter("adapter")
    @JvmStatic
    fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        view.adapter = adapter
    }

    @BindingAdapter("price")
    @JvmStatic
    fun setPriceText(view: TextView, price: Int){
        view.text = "${price}원"
    }

    @BindingAdapter("dateTime")
    @JvmStatic
    fun setDateText(view: TextView, isodate: Date){
        kotlin.runCatching {
            val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA)
            view.text = dateFormat.format(isodate)
        }.onFailure {
            it.printStackTrace()
        }
    }

    @BindingAdapter("selected")
    @JvmStatic
    fun setSelected(view: View, isSelected: Boolean?) {
        isSelected?.let {
            view.isSelected = it
        }
    }
}