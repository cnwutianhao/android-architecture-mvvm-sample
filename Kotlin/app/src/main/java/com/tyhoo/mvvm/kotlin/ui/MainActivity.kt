package com.tyhoo.mvvm.kotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tyhoo.mvvm.kotlin.R
import com.tyhoo.mvvm.kotlin.util.obtainViewModel
import com.tyhoo.mvvm.kotlin.util.replaceFragmentInActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findOrCreateViewFragment()
    }

    private fun findOrCreateViewFragment() =
        supportFragmentManager.findFragmentById(R.id.content_frame) ?: MainFragment.newInstance()
            .also {
                replaceFragmentInActivity(it, R.id.content_frame)
            }

    fun obtainViewModel(): MainViewModel = obtainViewModel(MainViewModel::class.java)
}