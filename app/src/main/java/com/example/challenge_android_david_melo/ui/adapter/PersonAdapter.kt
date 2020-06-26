package com.example.challenge_android_david_melo.ui.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_android_david_melo.R
import com.example.challenge_android_david_melo.model.PersonageResults
import com.example.challenge_android_david_melo.ui.utils.PersonageClicList
import com.example.challenge_android_david_melo.ui.viewHolder.PersonViewHolder

class PersonAdapter (var personas: ArrayList<PersonageResults>, private val personaOnclick : PersonageClicList):
    RecyclerView.Adapter<PersonViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PersonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.persona_item_card_view,parent,false))

    override fun getItemCount() = personas.size

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(personas[position])
        holder.itemView.setOnClickListener { personaOnclick.onClick(personas[position])


        }

    }

}
