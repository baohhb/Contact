package com.example.myapplication6

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

 class OutData(
    val name: String,
    val phoneNumber: String,
    val isMale: Boolean,
) : Serializable