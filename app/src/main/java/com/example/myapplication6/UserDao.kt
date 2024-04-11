package com.example.myapplication6

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM UserContact")
    fun getAllData(user: User): List<User>
    @Insert
   suspend fun insertUser(user: User)

    }



