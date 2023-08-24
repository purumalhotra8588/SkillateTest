package com.example.skillatetest

data class CountryData(
    val status: String,
    val statusCode: Int,
    val version: String,
    val access: String,
    val data: Map<String, Country>
)

data class Country(
    val country: String,
    val region: String
)

