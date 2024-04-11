package com.example.myapplication6

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "MainScreen"

class ListContactFragment2 : Fragment() {

    private val contactViewModel: ContactViewModel by activityViewModels()
//    private lateinit var userDao: UserDao


    private var param1: String? = null
    private var param2: String? = null
    private val contacts = arrayListOf<User>(
        User(0, "214", "9898", true)
    )


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
        val view = inflater.inflate(R.layout.fragment_listcontact, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnViewModel: Button = requireView().findViewById(R.id.btnViewModel)
        val btnAdd: Button = requireView().findViewById(R.id.btnGoToAdd)
        val rcv: RecyclerView = requireView().findViewById(R.id.recyclerViewContact)
        val layoutManager = LinearLayoutManager(context)
        val contactadapter = ContactAdapter(contacts, this)
        rcv.adapter = contactadapter
        rcv.layoutManager = layoutManager
//        val  db = Room.databaseBuilder(requireContext(),DataBase::class.java,"UserContact").build()
//        val userDao = db.userDao()
//        val users: List<User> = userDao.getAllData()

        contactViewModel.selectedItem.observe(viewLifecycleOwner, Observer { item ->
            contacts.add(
                User(
                    name = item.name,
                    phoneNumber = item.phoneNumber,
                    isMale = item.isMale
                )
            )
            contactadapter.notifyItemChanged(contacts.size)

        })

        btnViewModel.setOnClickListener {
            findNavController().navigate(R.id.action_ListContact_Fragment2_to_viewModelAddContactFragment3)
        }

        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_ListContact_Fragment2_to_AddContactFragment)

        }

        setFragmentResultListener("person") { _, person ->
            val name = person.getString("name")!!
            val phone = person.getString("phone")!!
            val isMale = person.getBoolean("isMale")
            val user = User(name = name, phoneNumber = phone, isMale = isMale)
            val db = Room.databaseBuilder(
                requireContext(),
                DataBase::class.java,
                "UserContact"
            ).fallbackToDestructiveMigration().build()

            CoroutineScope(Dispatchers.IO).launch {
                db.userDao().insertUser(user)
                db.close()
            }



        }
        setFragmentResultListener("person2") { _, person2 ->
            val nameEdit = person2.getString("nameEdit")!!
            val phoneEdit = person2.getString("phoneEdit")!!
            val gender = person2.getBoolean("gender")
            val position = person2.getInt("position", 1)
            if (position >= 0 && position < contacts.size) {
                contacts[position] = User(0, nameEdit, phoneEdit, gender)
            }
            contactadapter.notifyItemChanged(position)
        }

    }

    fun onClick(position: Int) {
        findNavController().navigate(R.id.action_ListContact_Fragment2_to_viewModelAddContactFragment3)

        val ds = contacts[position]
        val personv = Bundle().apply {
            putString("nameEdit", ds.name)
            putString("phoneEdit", ds.phoneNumber)
            putBoolean("isMaleEdit", ds.isMale)
            putInt("position", position)
        }
        setFragmentResult("personv", personv)
    }


}

