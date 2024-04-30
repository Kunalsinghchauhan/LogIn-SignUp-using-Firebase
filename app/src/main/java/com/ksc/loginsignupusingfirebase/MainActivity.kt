package com.ksc.loginsignupusingfirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ksc.loginsignupusingfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userList: ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUserList.layoutManager = LinearLayoutManager(this)
        userList = arrayListOf()
        auth = FirebaseAuth.getInstance()

        binding.tvCurrentUser.text = auth.currentUser?.email
        binding.btnSignOut.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LogInActivity::class.java))
            finish()
        }
        getUserDataFromFirebase()
    }

    private fun getUserDataFromFirebase() {

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {

                        val user = userSnapshot.getValue(User::class.java)
                        userList.add(user!!)
                    }
                    binding.rvUserList.adapter = UserAdapter(userList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Database error: ${error.message}")
            }

        })
    }
}