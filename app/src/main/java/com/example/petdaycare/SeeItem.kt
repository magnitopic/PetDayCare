package com.example.petdaycare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SeeItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_item)
        var pet = intent.getSerializableExtra("currentPet") as Pet
    }
}