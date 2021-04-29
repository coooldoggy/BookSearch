package com.coooldoggy.booksearch.network.data

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class BookSearchResponse(
    @SerializedName("meta")
    val meta: Meta,

    @SerializedName("documents")
    val documents: ArrayList<Documents>
)

data class Meta(
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("pageable_count")
    val pageableCount: Int,

    @SerializedName("is_end")
    val isEnd: Boolean
)

data class Documents(
    @SerializedName("title")
    val title: String,

    @SerializedName("contents")
    val contents: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("isbn")
    val isbn: String,

    @SerializedName("datetime")
    val datetime: String,

    @SerializedName("authors")
    val authors: ArrayList<String>,

    @SerializedName("publisher")
    val publisher: String,

    @SerializedName("translators")
    val translators: ArrayList<String>,

    @SerializedName("price")
    val price: Int,

    @SerializedName("sale_price")
    val salePrice: Int,

    @SerializedName("thumbnail")
    val thumbnail: String,

    @SerializedName("status")
    val status: String

)
