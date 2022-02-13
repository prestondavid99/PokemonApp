package com.example.pokemonapplication.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapplication.R
import com.example.pokemonapplication.data.Pokemon

class MainRecyclerAdapter(val context: Context, val pokemon: List<Pokemon>):
RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>()
{
    inner class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
        val nameText = itemView.findViewById<TextView>(R.id.nameText)
        val imageURL = itemView.findViewById<ImageView>(R.id.pokemonImage)
        val pokemonID = itemView.findViewById<TextView>(R.id.pokemonID)

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.pokemon_grid, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val monster = pokemon[position]
        with(holder) {
            nameText?.let {
                it.text = pokemon[position].name
                it.contentDescription = pokemon[position].name
            }

        }
    }

    override fun getItemCount() = pokemon.size
}