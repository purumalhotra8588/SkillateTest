package com.example.skillatetest.data

data class Book(
    val id: String,
    val image: String,
    val hits: Int,
    val alias: String,
    val title: String,
    val lastChapterDate: Long,
    var isFavorite: Boolean
)
