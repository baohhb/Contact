package com.example.myapplication6

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.IDN

@Entity(tableName = "UserContact")
data class User(
    @PrimaryKey(autoGenerate = true)
     val id: Long = 0,
     val name: String,
     val phoneNumber: String,
     val isMale: Boolean,
)

