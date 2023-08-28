package com.example.skillatetest

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "registered_emails")
data class RegisteredEmail(
    @PrimaryKey val email: String
)

