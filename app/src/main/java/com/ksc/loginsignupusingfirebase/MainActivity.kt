package com.ksc.loginsignupusingfirebase

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.ksc.loginsignupusingfirebase.databinding.ActivityLogInBinding
import com.ksc.loginsignupusingfirebase.databinding.ActivityMainBinding
import com.ksc.loginsignupusingfirebase.databinding.ActivitySignUpBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.tvCurrentUser.text = auth.currentUser?.email.toString()

        binding.btnSignOut.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LogInActivity::class.java))
            finish()
        }
    }
}