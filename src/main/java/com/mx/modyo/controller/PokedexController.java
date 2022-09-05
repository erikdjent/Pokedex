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
	
	@GetMapping("/pokemon/{id}")
    public ResponseEntity<List<Pokemon>> getPokemon(@PathVariable String id) {
        return new ResponseEntity<>(pokemonService.getPokemon(id), HttpStatus.OK);
    }

    @GetMapping("/listPokemon")
    public ResponseEntity<List<Pokemons>> getPokemons(@RequestParam(value = "offset", required = false) String offset, @RequestParam(value = "limit", required = false) String limit) {
        return new ResponseEntity<>(pokemonService.listPokemons(offset,limit), HttpStatus.OK);
    }

}
