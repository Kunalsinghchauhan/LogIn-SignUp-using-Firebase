package com.ksc.loginsignupusingfirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val userList: ArrayList<User>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_items, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.name?.text = currentItem.name
        holder.email?.text = currentItem.email
        holder.number?.text = currentItem.number
        holder.userName?.text = currentItem.userName


    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView? = itemView.findViewById(R.id.tv_user_name)
    val email: TextView? = itemView.findViewById(R.id.tv_user_email)
    val number: TextView? = itemView.findViewById(R.id.tv_user_number)
    val userName: TextView? = itemView.findViewById(R.id.tv_user_username)

}
