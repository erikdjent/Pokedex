package com.mx.modyo.model;

import java.util.ArrayList;

import lombok.Data;

public @Data class ListPokemon {
	
    private String name;
    private String url;
    private ArrayList<Pokemon> roots;
}
