package com.ksc.loginsignupusingfirebase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ksc.loginsignupusingfirebase.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.tvLogIn.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
            finish()
        }
        binding.btnSignUP.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val number = binding.etNumber.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()
            val user = User(name, email, number, password)
            if (name.isEmpty() || email.isEmpty() || number.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Snackbar.make(binding.root, "Please Fill All Boxes ", Snackbar.LENGTH_SHORT).show()
            } else
                if (password != confirmPassword) {
                    Snackbar.make(binding.root, "Please Check Your Password", Snackbar.LENGTH_SHORT)
                        .show()
                } else {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                databaseReference =
                                    FirebaseDatabase.getInstance().getReference("Users")
                                databaseReference.child(number).setValue(user)
                                    .addOnSuccessListener {
                                        Snackbar.make(
                                            binding.root,
                                            "Registration Successful",
                                            Snackbar.LENGTH_SHORT
                                        ).show()
                                        auth.signOut()
                                        startActivity(Intent(this, LogInActivity::class.java))
                                        finish()
                                    }
                            } else {
                                Snackbar.make(
                                    binding.root,
                                    task.exception?.message.toString(),
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
        }

    }

}