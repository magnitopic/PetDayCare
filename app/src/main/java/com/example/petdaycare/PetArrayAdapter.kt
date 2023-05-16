package com.example.petdaycare

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class WordArrayAdapter(context: Context, viewToPaint: Int, private val petList: ArrayList<Pet>) :
    ArrayAdapter<Pet>(context, viewToPaint, petList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val currentListItem = inflater.inflate(R.layout.activity_item_list_pet, null)


        val nameText = currentListItem.findViewById<TextView>(R.id.textViewName)
        val typeText = currentListItem.findViewById<TextView>(R.id.textViewType)
        val genderImage = currentListItem.findViewById<ImageView>(R.id.imageViewGender)

        nameText.text = petList.get(position).nombre
        typeText.text = petList.get(position).raza
        if (petList.get(position).sexo == "femenino")
            genderImage.setImageResource(R.drawable.female)
        else
            genderImage.setImageResource(R.drawable.male)
        return currentListItem
    }
}