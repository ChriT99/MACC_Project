package com.example.ec_app

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val  btnSignUpNow = findViewById<TextView>(R.id.signupText)
        btnSignUpNow.setOnClickListener {
            Intent(this, Register::class.java).also{
                startActivity(it)
            }
        }
        val btnLogin = findViewById<Button>(R.id.LoginButton)
        btnLogin.setOnClickListener {

            loginUser()
        }
    }

    private fun loginUser(){
        val email = findViewById<EditText>(R.id.LoginUsername).text.toString()
        val password = findViewById<EditText>(R.id.LoginPassword).text.toString()
        val user = Firebase.auth.currentUser


        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill all fiels", Toast.LENGTH_SHORT)
                .show()
            return
        }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success
                        val it = Intent(this, MainActivity::class.java)
                        startActivity(it)


                        Toast.makeText(
                            baseContext,
                            "Authentication Success.",
                            Toast.LENGTH_SHORT,
                        ).show()


                    } else {
                        // If sign in fails,
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }


    }


}