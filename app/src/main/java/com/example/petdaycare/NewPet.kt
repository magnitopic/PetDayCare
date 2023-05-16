package com.example.petdaycare

import android.media.MediaParser.SeekPoint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NewPet : AppCompatActivity() {
    lateinit var editTextName: EditText
    lateinit var editTextType: EditText
    lateinit var spinerGender: Spinner
    lateinit var editTextComposition: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_pet)

        editTextName = findViewById(R.id.editTextNombre)
        editTextType = findViewById(R.id.editTextRaza)
        spinerGender = findViewById(R.id.generoSpinner)
        editTextComposition = findViewById(R.id.weightInput)

        val spinner: Spinner = findViewById(R.id.generoSpinner)
        // Ponemos las opciones del spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.generos,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        val newPetButton = findViewById<Button>(R.id.addPetButton)
        // si newPetButton es presionado y los campos no está vacios, creamos el nuevo elemento en la base de datos
        newPetButton.setOnClickListener {
            if (checks())
                    createPet()
            else
                lauchAlert("Algunos de los campos del formulario están vacios")
        }
    }

    fun checks(): Boolean {
        if (editTextName.text.toString().isEmpty()
            || editTextComposition.text.toString().isEmpty()
            || editTextType.text.toString().isEmpty()
        )
            return false
        return true
    }

    fun createPet() {
        val db = Firebase.firestore
        val pet = hashMapOf(
            "name" to editTextName.text.toString(),
            "type" to editTextType.text.toString(),
            "gender" to spinerGender.selectedItem.toString(),
            "composition" to editTextComposition.text.toString()
        )
        db.collection("pets")
            .add(pet)
            .addOnSuccessListener { documentReference ->
                lauchToast("Mascota registrada con exito")
            }
            .addOnFailureListener { e ->
                lauchAlert("Se ha producido un error durante la operación.")
            }
    }

    fun lauchToast(message: String) {
        val t = Toast.makeText(applicationContext, message, Toast.LENGTH_LONG)
        t.show()
    }

    fun lauchAlert(message: String) {
        val a = AlertDialog.Builder(this)
        a.setTitle("Error!")
        a.setMessage(message)
        a.setPositiveButton("Aceptar", null)
        a.create().show()
    }
}