package com.tyhoo.mvvm.kotlin.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tyhoo.mvvm.kotlin.ui.MainViewModel

class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) = MainViewModel() as T

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance() = INSTANCE ?: synchronized(ViewModelFactory::class.java) {
            INSTANCE ?: ViewModelFactory().also { INSTANCE = it }
        }
    }
}