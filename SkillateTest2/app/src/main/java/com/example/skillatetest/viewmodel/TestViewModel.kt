package com.example.skillatetest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skillatetest.data.Book

class TestViewModel :ViewModel() {

    val openLogin = MutableLiveData<Boolean>()
    val openBookPage = MutableLiveData<Boolean>()
    val logout = MutableLiveData<Boolean>()
    val openDescription = MutableLiveData<Boolean>()

    var selectedBook: Book? = null

}