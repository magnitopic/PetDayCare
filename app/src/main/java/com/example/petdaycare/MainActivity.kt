package com.example.petdaycare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.loginButton).setOnClickListener {
            login()
        }
        val registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener{
            register()
        }
    }

    fun register(){
        val editTextEmail = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val editTextPass = findViewById<EditText>(R.id.editTextTextPassword)

        if (editTextEmail.text.isNotEmpty() && editTextPass.text.isNotEmpty()){
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(editTextEmail.text.toString(),
                    editTextPass.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        lauchToast("Cuenta creada correctamente! Se necesita hacer Login")
                    }else{
                        lauchAlert("Se ha producido un error durante la operación.")
                    }
                }
        }else
            lauchAlert("Algunos de los campos están vacios")
    }

    fun login(){
        val editTextEmail = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val editTextPass = findViewById<EditText>(R.id.editTextTextPassword)

        if (editTextEmail.text.isNotEmpty() && editTextPass.text.isNotEmpty()){
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(editTextEmail.text.toString(),
                    editTextPass.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        var i = Intent(this, MainScreen::class.java)
                        lauchToast("Login correcto!")
                        startActivity(i)
                    }else{
                        lauchAlert("Se ha producido un error durante la operación.")
                    }
                }
        }else
            lauchAlert("Algunos de los campos están vacios")
    }

    fun lauchToast(message : String){
        val t = Toast.makeText(applicationContext, message, Toast.LENGTH_LONG)
        t.show()
    }

    fun lauchAlert(message: String){
        val a = AlertDialog.Builder(this)
        a.setTitle("Error!")
        a.setMessage(message)
        a.setPositiveButton("Aceptar", null)
        a.create().show()
    }
}