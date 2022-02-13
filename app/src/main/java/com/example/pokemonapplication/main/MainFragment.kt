package com.example.pokemonapplication.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapplication.R
import com.example.pokemonapplication.ui.main.MainViewModel


class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.pokemon_grid, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.loadPokemonPaginated()

        return view
    }

}
