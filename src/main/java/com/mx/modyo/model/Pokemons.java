package com.mx.modyo.model;

import java.util.ArrayList;

import lombok.Data;

public @Data class Pokemons {
	
	 private int count;
	 private String next;
	 private Object previous;
	 private ArrayList<ListPokemon> results;
	
}
