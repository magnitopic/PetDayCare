package com.example.petdaycare

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainScreen : AppCompatActivity() {
    val pets = arrayListOf<Pet>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        val petList = findViewById<ListView>(R.id.petList)
        // star new activity when add pet button is pressed
        findViewById<ImageButton>(R.id.newPetButton).setOnClickListener {
            var i = Intent(this, NewPet::class.java)
            startActivity(i)
        }
        // get the pets to be listed
        getPets()
        // set petList's emptyView
        petList.emptyView = findViewById<TextView>(R.id.empty)
        // add onClick for list pets
        petList.setOnItemClickListener { parent, view, position, id ->
            val i = Intent(applicationContext, SeeItem::class.java)
                .putExtra("currentPet", pets[position])
            startActivity(i)
        }
    }

    fun getPets() {
        val db = Firebase.firestore
        val petList = findViewById<ListView>(R.id.petList)

        db.collection("pets")
            .get()
            .addOnSuccessListener { result ->
                for (d in result) {
                    val newPet = Pet(
                        d.data["name"].toString(),
                        d.data["type"].toString(),
                        d.data["gender"].toString(),
                        d.data["composition"].toString()
                    )
                    Log.d("tes", newPet.nombre)
                    pets.add(newPet)
                }
                var petArrayAdapter =
                    PetArrayAdapter(applicationContext, R.layout.activity_item_list_pet, pets)
                petList.adapter = petArrayAdapter
            }
    }

    override fun onBackPressed() {}
}