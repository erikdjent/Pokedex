package com.mx.modyo.service;


import java.util.List;

import com.mx.modyo.model.Pokemon;
import com.mx.modyo.model.Pokemons;


public interface IPokemonService {
	
	public List<Pokemon> getPokemon(String id);
	
	public List<Pokemons> listPokemons(String offset, String limit);

}
