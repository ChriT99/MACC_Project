package com.example.ec_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var itemList = mutableListOf(
            Item("Announcement 1", R.drawable.googleimage),
            Item("Announcement 2", R.drawable.googleimage),
            Item("Announcement 3", R.drawable.googleimage),
            Item("Announcement 4", R.drawable.googleimage),
            Item("Announcement 5", R.drawable.googleimage),
            Item("Announcement 6", R.drawable.googleimage),
            Item("Announcement 7", R.drawable.googleimage),
            Item("Announcement 8", R.drawable.googleimage),
            Item("Announcement 9", R.drawable.googleimage),
            Item("Announcement 10", R.drawable.googleimage),

        )
        val adapter = ItemAdapter(itemList)
        findViewById<RecyclerView>(R.id.rvHomepage).adapter = adapter
        findViewById<RecyclerView>(R.id.rvHomepage).layoutManager = LinearLayoutManager(this)

        //authentication
        auth = Firebase.auth
        val  btnGoTo= findViewById<Button>(R.id.btnGoTo)
        val tvHomepage = findViewById<TextView>(R.id.tvHomepage)

        if (auth.currentUser != null) {
            val email = auth.currentUser?.email
            tvHomepage.text = "$email"
            btnGoTo.text = "Logout"
        } else {
            btnGoTo.text = "Login"
        }

        btnGoTo.setOnClickListener {
            if (auth.currentUser != null) {
                auth.signOut()
                tvHomepage.text = "Homepage"
                btnGoTo.text = "Login"
            } else {
                Intent(this, Login::class.java).also{
                    startActivity(it)
                }
            }
        }



    }


}