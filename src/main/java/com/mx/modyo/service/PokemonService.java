package com.mx.modyo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;


import com.mx.modyo.model.Characteristic;
import com.mx.modyo.model.Evolution;
import com.mx.modyo.model.ListPokemon;
import com.mx.modyo.model.Pokemon;
import com.mx.modyo.model.Pokemons;
import com.mx.modyo.utils.ApiUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PokemonService implements IPokemonService{
	
	WebClient webClient = WebClient.builder().baseUrl("https://pokeapi.co/api/v2")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

	@Override
	public List<Pokemon> getPokemon(String id) {


		Flux<Pokemon> rootFlux = webClient.get().uri("/pokemon/" + id).retrieve()
				.bodyToFlux(Pokemon.class).onErrorResume(WebClientResponseException.class,
						ex -> ex.getRawStatusCode() == 404 ? Flux.empty() : Mono.error(ex));
		List<Pokemon> pokemon = rootFlux.collect(Collectors.toList()).share().block();
		
		for (Pokemon root : pokemon) {
			Flux<Characteristic> cFlux = webClient.get().uri("/characteristic/" + id).retrieve()
					.bodyToFlux(Characteristic.class).onErrorResume(WebClientResponseException.class,
							ex -> ex.getRawStatusCode() == 404 ? Flux.empty() : Mono.error(ex));
			Characteristic characteric = cFlux.share().blockFirst();
			root.setCharacteristic(characteric);
		}
		
		for (Pokemon root : pokemon) {
			Flux<Evolution> cFlux = webClient.get().uri("/evolution-chain/" + id).retrieve()
					.bodyToFlux(Evolution.class).onErrorResume(WebClientResponseException.class,
							ex -> ex.getRawStatusCode() == 404 ? Flux.empty() : Mono.error(ex));
			Evolution evolution = cFlux.share().blockFirst();
			root.setEvolution(evolution);
		}
		
		return pokemon;
	
	}

	@Override
	public List<Pokemons> listPokemons(String offset, String limit) {


		if (offset != null && limit != null) {

			if (ApiUtils.isNumeric(offset) && ApiUtils.isNumeric(limit)) {
				Flux<Pokemons> pokemonFlux = webClient
						.get().uri("/pokemon?offset=" + offset + "&limit=" + limit)
						.retrieve().bodyToFlux(Pokemons.class);

				List<Pokemons> list = pokemonFlux.collect(Collectors.toList()).share().block();

				for (Pokemons iter : list) {
					for (ListPokemon result : iter.getResults()) {
						Flux<Pokemon> rootFlux = WebClient.create(result.getUrl()).get().retrieve().bodyToFlux(Pokemon.class)
								.onErrorResume(WebClientResponseException.class,
										ex -> ex.getRawStatusCode() == 404 ? Flux.empty() : Mono.error(ex));
						List<Pokemon> pokemon = rootFlux.collect(Collectors.toList()).share().block();
						result.setRoots(new ArrayList<>(pokemon));
					}
				}

				return list;

			} else {
				Flux<Pokemons> pokemonFlux = webClient.get().uri("/pokemon/").retrieve()
						.bodyToFlux(Pokemons.class);
				
				List<Pokemons> list = pokemonFlux.collect(Collectors.toList()).share().block();

				for (Pokemons iter : list) {
					for (ListPokemon result : iter.getResults()) {
						Flux<Pokemon> rootFlux = WebClient.create(result.getUrl()).get().retrieve().bodyToFlux(Pokemon.class)
								.onErrorResume(WebClientResponseException.class,
										ex -> ex.getRawStatusCode() == 404 ? Flux.empty() : Mono.error(ex));
						List<Pokemon> pokemon = rootFlux.collect(Collectors.toList()).share().block();
						result.setRoots(new ArrayList<>(pokemon));
					}
				}

				return list;
				
			}

		}

		Flux<Pokemons> pokemonFlux = webClient.get().uri("/pokemon/").retrieve()
				.bodyToFlux(Pokemons.class);
		
		List<Pokemons> list = pokemonFlux.collect(Collectors.toList()).share().block();

		for (Pokemons iter : list) {
			for (ListPokemon result : iter.getResults()) {
				Flux<Pokemon> rootFlux = WebClient.create(result.getUrl()).get().retrieve().bodyToFlux(Pokemon.class)
						.onErrorResume(WebClientResponseException.class,
								ex -> ex.getRawStatusCode() == 404 ? Flux.empty() : Mono.error(ex));
				List<Pokemon> pokemon = rootFlux.collect(Collectors.toList()).share().block();
				result.setRoots(new ArrayList<>(pokemon));
			}
		}

		return list;
		
	
	}

	

}
