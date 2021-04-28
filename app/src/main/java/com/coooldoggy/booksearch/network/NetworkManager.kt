package com.coooldoggy.booksearch.network

import android.content.Context
import android.util.Log
import com.coooldoggy.booksearch.BASE_BOOK_SEARCH_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private val TAG = NetworkManager::class.java.simpleName

    fun <S> createService(serviceClass: Class<S>): S {
        return buildRetrofitApi().create(serviceClass)
    }

    private fun buildRetrofitApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_BOOK_SEARCH_URL)
            .client(provideOkHttpClient(provideLoggingInterceptor()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
        return builder.build()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message -> Log.d(TAG, message) }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}