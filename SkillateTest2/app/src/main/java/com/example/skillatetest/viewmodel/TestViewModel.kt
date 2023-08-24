package com.example.skillatetest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestViewModel :ViewModel() {

    val openLogin = MutableLiveData<Boolean>()
    val openBookPage = MutableLiveData<Boolean>()
    val logout = MutableLiveData<Boolean>()
}