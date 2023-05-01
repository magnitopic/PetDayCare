package com.example.petdaycare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.loginButton).setOnClickListener {
            var i = Intent(this, MainScreen::class.java)
            startActivity(i)
        }
    }
}