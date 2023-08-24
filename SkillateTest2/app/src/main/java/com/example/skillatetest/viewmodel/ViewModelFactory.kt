package com.example.skillatetest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        return when{
            modelClass.isAssignableFrom(TestViewModel::class.java) -> TestViewModel() as T
            else -> throw IllegalArgumentException("ViewModelClass not found")
        }
    }

}