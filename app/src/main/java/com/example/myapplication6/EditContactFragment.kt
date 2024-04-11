package com.example.myapplication6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController

private const val TAG = "EditScreen"
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EditContactFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
   private val nameEdit : TextView by lazy { requireView().findViewById(R.id.tvEditName) }
   private val phoneEdit : TextView by lazy { requireView().findViewById(R.id.tvEditPhone) }
   private val imageEdit : ImageView by lazy { requireView().findViewById(R.id.editImage) }
   private val radioButtonGender: RadioButton by lazy {requireView(). findViewById(R.id.rBtnEditgender) }
   private val saveButton: Button by lazy {requireView(). findViewById(R.id.btnSave) }
   private val deleteButton: Button by lazy {requireView(). findViewById(R.id.btnDelete) }
   private val contactName : String?= null
   private val contactPhone : String?= null

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
        return inflater.inflate(R.layout.fragment_edti, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("personv") { _, personv ->

            val contactName = personv.getString("nameEdit")
            val contactPhone = personv.getString("phoneEdit")
            val gender = personv.getBoolean("isMaleEdit",true)
            if (gender == true) imageEdit.setImageResource(R.drawable.avatarboy) else imageEdit.setImageResource(
                R.drawable.images
            )
            val position = personv.getInt("position",-1)
            nameEdit.text=contactName
            phoneEdit.text=contactPhone
            saveButton.setOnClickListener{
                val nameEdit = nameEdit.text.toString().trim()
                val phoneEdit = phoneEdit.text.toString().trim()
                var isMale = true
                isMale = radioButtonGender.isChecked

                val person2 = Bundle().apply {
                    putString("nameEdit",nameEdit)
                    putString("phoneEdit",phoneEdit)
                    putBoolean("gender",isMale)
                    putInt("position",position)
                }
                setFragmentResult("person2",person2)
                findNavController().navigateUp()
            }
            deleteButton.setOnClickListener{
                val person3 = Bundle().apply {
                    putString("deleteName",contactName)
                    putString("deletePhone",contactPhone)
                    putInt("position",position)

                }
                setFragmentResult("person3",person3)
                findNavController().navigateUp()
            }
        }

    }


   }


