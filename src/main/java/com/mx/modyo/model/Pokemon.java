package com.mx.modyo.model;


import java.util.ArrayList;

import lombok.Data;

public @Data class Pokemon {
	
    private String name;
    private Sprites sprites;
    private ArrayList<Type> types;
	private ArrayList<Ability> abilities;
	private int weight;
	private Characteristic characteristic;
	private Evolution evolution;

}
