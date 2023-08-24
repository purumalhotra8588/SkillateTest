package com.example.skillatetest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestViewModel :ViewModel() {

    val openLogin = MutableLiveData<Boolean>()
}