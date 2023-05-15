package com.example.petdaycare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView

class MainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val empty = findViewById<TextView>(R.id.empty)
        val petList = findViewById<ListView>(R.id.petList)
        petList.emptyView = empty
        findViewById<ImageButton>(R.id.newPetButton).setOnClickListener {
            var i = Intent(this, NewPet::class.java)
            startActivity(i)
        }

    }
}