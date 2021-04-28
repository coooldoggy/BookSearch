package com.coooldoggy.booksearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.coooldoggy.booksearch.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addBookSearchFragment()
    }

    private fun addBookSearchFragment(){
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view, BookSearchFragment())
        }
    }
}