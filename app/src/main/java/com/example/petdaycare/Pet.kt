package com.example.petdaycare

import java.io.Serializable

data class Pet(val nombre: String,  val raza: String, val sexo: String, val peso: String):
    Serializable