package com.example.myapplication6

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.myapplication6.databinding.FragmentViewModelAddContactBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class ViewModelAddContactFragment : Fragment() {
    private val contactViewModel: ContactViewModel by activityViewModels()

    private var  _binding :FragmentViewModelAddContactBinding ?= null
    private val  binding get() = _binding!!

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
        _binding = FragmentViewModelAddContactBinding.inflate(inflater, container, false)

   return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     binding.btnAddViewModel  .setOnClickListener {
            val addName = binding.tvAddNameViewModel .text.toString().trim()
            val addPhone = binding.tvAddPhoneViewModel .text.toString().trim()
            var isMale = true
            isMale = binding.rBtnMaleViewModel .isChecked
         contactViewModel.selectItem(OutData(addName,addPhone,isMale))
            findNavController().navigateUp()


        }

}

    private fun addContact() {
        val tvAddName : TextView = requireView().findViewById(R.id.tvAddNameViewModel)
        val tvAddPhone : TextView = requireView().findViewById(R.id.tvAddPhoneViewModel)
        val rBtnMale : RadioButton = requireView() . findViewById(R.id.rBtnMaleViewModel)

        val addName = tvAddName.text.toString().trim()
        val addPhone = tvAddPhone.text.toString().trim()
        var isMale = true
        isMale = rBtnMale.isChecked

        val personViewModel = Bundle().apply {
            putString("name", addName)
            putString("phone", addPhone)
            putBoolean("isMale", isMale)
        }
        setFragmentResult("personViewModel", personViewModel)
        Log.d("TAG:AddContact","$personViewModel")
        findNavController().navigateUp()
   }

}



