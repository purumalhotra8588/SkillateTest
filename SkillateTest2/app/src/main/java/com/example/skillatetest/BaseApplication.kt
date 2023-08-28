package com.example.skillatetest

import android.app.Application
import android.content.Context
import androidx.room.Room

class BaseApplication : Application() {

    companion object {
        private lateinit var context: Context

        fun getContext(): Context {
            return context;
        }

        fun setContext(con: Context){
            context = con
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this

    }

    }
