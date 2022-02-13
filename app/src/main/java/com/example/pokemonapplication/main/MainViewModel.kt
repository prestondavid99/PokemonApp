package com.example.pokemonapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapplication.PAGE_SIZE
import com.example.pokemonapplication.R
import com.example.pokemonapplication.api.PokemonRepository
import com.example.pokemonapplication.models.PokemonListEntry
import com.example.pokemonapplication.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private var currPage = 0



    var pokemonList = mutableStateOf<List<PokemonListEntry>>(listOf())
    var endReached = mutableStateOf(false)

    init {
        loadPokemonPaginated()
    }

    fun loadPokemonPaginated() {
        viewModelScope.launch {
            val result = repository.getPokemonList(PAGE_SIZE, currPage * PAGE_SIZE)
            when(result) {
                is Resource.Success -> {
                    endReached.value = currPage * PAGE_SIZE >= result.data!!.count
                    val pokemonEntries = result.data.results.mapIndexed {index, entry ->
                        val number = if(entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }
                        val url = "https://pokeapi.co/api/v2/pokemon/${number}.png"
                        PokemonListEntry(entry.name, url, number.toInt())
                    }
                    currPage++
                    pokemonList.value += pokemonEntries
                }
                is Resource.Error -> {

                }
            }
        }
    }


}
