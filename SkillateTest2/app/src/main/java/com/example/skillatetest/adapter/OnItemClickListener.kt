package com.example.skillatetest.adapter

import android.view.View

interface OnItemClickListener {
    fun onItemClick(view: View, position: Int = -1, data: Any? = null)
}