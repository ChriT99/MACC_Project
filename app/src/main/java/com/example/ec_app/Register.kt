package com.example.ec_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {
    private lateinit var  auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        val registerButton = findViewById<Button>(R.id.RegisterButton)
        registerButton.setOnClickListener {
            registerUser()
        }


    }


    private fun registerUser(){
        val email = findViewById<EditText>(R.id.RegisterUsername).text.toString()
        val password = findViewById<EditText>(R.id.RegisterPassword).text.toString()

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT)
                .show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    val it = Intent(this, Login::class.java)
                    startActivity(it)

                    Toast.makeText(
                        baseContext,
                        "Registration Success.",
                        Toast.LENGTH_SHORT,
                    ).show()

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext,
                        "Registration failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }




    }
}