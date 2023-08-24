package com.spyneai.dashboard.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skillatetest.TestViewModel

class ViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        return when{
            modelClass.isAssignableFrom(TestViewModel::class.java) -> TestViewModel() as T
            else -> throw IllegalArgumentException("ViewModelClass not found")
        }
    }

}