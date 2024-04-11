package com.example.myapplication6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val ds: List<User>, private val listener:ListContactFragment2) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

//    interface ListContactFragment2 {
//        fun onClick(position: Int)
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contac, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(ds[position])

    }

    override fun getItemCount(): Int {
        return ds.size
    }




    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        private val nameTextView: TextView = itemView.findViewById(R.id.name)
        val phoneNumberTextView: TextView = itemView.findViewById(R.id.sdt)
        val imageView: ImageView = itemView.findViewById(R.id.avata)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onClick(position)
            }
        }

        fun bindView(User: User): View {
            itemView.apply {
                nameTextView.text = User.name
                phoneNumberTextView.text = User.phoneNumber
            }
            if (User.isMale) {
                imageView.setImageResource(R.drawable.avatarboy)
            } else {
                imageView.setImageResource(R.drawable.images)
            }
            return itemView

        }
    }
}