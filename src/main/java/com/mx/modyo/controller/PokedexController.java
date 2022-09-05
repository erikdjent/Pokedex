package com.mx.modyo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.modyo.model.Pokemon;
import com.mx.modyo.model.Pokemons;
import com.mx.modyo.service.IPokemonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PokedexController {
	
	@Autowired
	private IPokemonService pokemonService;
	
	@GetMapping (value = {"/pokemon", "/"})
	public String inicial() {
		
		String mensajeString = "1. Para obtener el listado de los Pokemons ingrese la peticion /listPokemon y por default establecera el paginado 0 - 20 \n"
				+ " el paginado lo pueden enviar en la misma peticion como parte de los argumentos de la peticion ejemplo: /listPokemon?offset=0&limit=30 \n"
				+ " 2. Para buscar un Pokemon basta con indicar la peticion /pokemon/{id} o {nombre}";
		
		return mensajeString;
		
	}
	
	@GetMapping("/pokemon/{id}")
    public ResponseEntity<List<Pokemon>> getPokemon(@PathVariable String id) {
        return new ResponseEntity<>(pokemonService.getPokemon(id), HttpStatus.OK);
    }

    @GetMapping("/listPokemon")
    public ResponseEntity<List<Pokemons>> getPokemons(@RequestParam(value = "offset", required = false) String offset, @RequestParam(value = "limit", required = false) String limit) {
        return new ResponseEntity<>(pokemonService.listPokemons(offset,limit), HttpStatus.OK);
    }

}
