package com.ksc.loginsignupusingfirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.ksc.loginsignupusingfirebase.databinding.ActivityLogInBinding
class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener
    private var isRedirectedFromSignUp = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.btnLogIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Snackbar.make(binding.root, "Please Fill All Boxes ", Snackbar.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }.addOnFailureListener {
                    Snackbar.make(
                        binding.root,
                        "Problem Occurred, Please try again!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}