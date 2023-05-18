package com.example.petdaycare

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class SeeItem : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_item)
        var pet = intent.getSerializableExtra("currentPet") as Pet
        findViewById<TextView>(R.id.viewNombre).text = "Nombre: " + pet.nombre
        findViewById<TextView>(R.id.viewGender).text = pet.sexo
        findViewById<TextView>(R.id.viewType).text = "Raza: " + pet.raza
        findViewById<TextView>(R.id.viewComposition).text = pet.peso + " KG"
        if (pet.sexo == "Femenino")
            findViewById<ImageView>(R.id.genderImage).setImageResource(R.drawable.female)
        else
            findViewById<ImageView>(R.id.genderImage).setImageResource(R.drawable.male)
    }
}