package com.coooldoggy.booksearch.network.data

import java.io.Serializable

data class BookItem(
    val documents: Documents,
    var isFavorite: Boolean
): Serializable
