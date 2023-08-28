package com.example.skillatetest

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RegisteredEmailDao {
    @Query("SELECT * FROM registered_emails")
    fun getAllRegisteredEmails(): LiveData<List<RegisteredEmail>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRegisteredEmail(email: RegisteredEmail)
}
