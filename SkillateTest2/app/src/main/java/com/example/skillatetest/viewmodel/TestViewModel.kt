package com.example.skillatetest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skillatetest.AppDatabase
import com.example.skillatetest.BaseApplication
import com.example.skillatetest.RegisteredEmail
import com.example.skillatetest.data.Book

class TestViewModel :ViewModel() {

    val openLogin = MutableLiveData<Boolean>()
    val openBookPage = MutableLiveData<Boolean>()
    val logout = MutableLiveData<Boolean>()
    val openDescription = MutableLiveData<Boolean>()

    private val appDatabase = AppDatabase.getInstance(BaseApplication.getContext())

    var selectedBook: Book? = null

    val registeredEmailDao = appDatabase.registeredEmailDao()

    // Retrieve all registered emails
    val allRegisteredEmails: LiveData<List<RegisteredEmail>> = registeredEmailDao.getAllRegisteredEmails()

    fun checkIfEmailIsRegistered(email: String): Boolean {
        val registeredEmails = allRegisteredEmails.value
        return registeredEmails?.any { it.email == email } == true
    }

    suspend fun addEmailToRegisteredList(email: String) {
        val registeredEmailDao = appDatabase.registeredEmailDao()
        val registeredEmail = RegisteredEmail(email)
        registeredEmailDao.insertRegisteredEmail(registeredEmail)
    }



}