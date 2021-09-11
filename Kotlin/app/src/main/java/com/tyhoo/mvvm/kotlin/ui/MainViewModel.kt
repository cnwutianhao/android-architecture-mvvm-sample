package com.tyhoo.mvvm.kotlin.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.tyhoo.mvvm.kotlin.R
import com.tyhoo.mvvm.kotlin.data.User

class MainViewModel : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    private val _userData = MutableLiveData<String>()
    val userData: LiveData<String>
        get() = _userData

    private var jsonResponse = ""

    fun start(context: Context) {
        networkRequest(context)
    }

    private fun networkRequest(context: Context) {
        _dataLoading.value = true

        val queue = Volley.newRequestQueue(context)
        val url = "https://api.github.com/users/cnwutianhao"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                jsonResponse = response
                _dataLoading.value = false
                _userData.value = response
            }) {
            _dataLoading.value = false
            _userData.value = ""
        }

        queue.add(stringRequest)
    }

    fun parseJson(context: Context) {
        if (jsonResponse.isEmpty()) {
            return
        }

        val gson = Gson()
        val user = gson.fromJson(jsonResponse, User::class.java)
        val text = context.getString(
            R.string.parse_text, user.login, user.html_url,
            user.name, user.company, user.location
        )

        _userData.value = text
    }
}