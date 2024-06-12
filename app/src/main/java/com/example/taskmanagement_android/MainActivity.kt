package com.example.taskmanagement_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button


class MainActivity : AppCompatActivity() {
    private lateinit var loginButton: Button
    private lateinit var RegisterButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        loginButton = findViewById(R.id.loginbtn)
        RegisterButton = findViewById(R.id.registerbtn)

        loginButton.setOnClickListener {
            Log.d("MainActivity", "Login button clicked")
            startActivity(Intent(this, LoginActivity::class.java))
        }

        RegisterButton.setOnClickListener {
            Log.d("MainActivity", "Register button clicked")
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}