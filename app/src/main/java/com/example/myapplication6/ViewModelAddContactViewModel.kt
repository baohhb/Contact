package com.example.myapplication6

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {
    private val mutableSelectedItem = MutableLiveData<OutData>()
    val selectedItem: LiveData<OutData> get() = mutableSelectedItem

    fun selectItem(item :OutData) {
        mutableSelectedItem.value=item

    }


}

