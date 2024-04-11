package com.example.myapplication6

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.room.Room


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddContactFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_addcontact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val btnAdd : Button = requireView().findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener {
            addContact()
            Log.d("TAG","${addContact()}")
        }

    }

    private fun addContact() {
        val tvAddName : TextView = requireView().findViewById(R.id.tvAddName)
        val tvAddPhone : TextView = requireView().findViewById(R.id.tvAddPhone)
        val rBtnMale : RadioButton = requireView() . findViewById(R.id.rBtnMale)
        val addName = tvAddName.text.toString().trim()
        val addPhone = tvAddPhone.text.toString().trim()
        var isMale = true
        isMale = rBtnMale.isChecked

       val person = Bundle().apply {
           putString("name", addName)
           putString("phone", addPhone)
           putBoolean("isMale", isMale)
       }
       setFragmentResult("person", person)
        Log.d("TAG:AddContact","$person")
        findNavController().navigateUp()
    }

}
