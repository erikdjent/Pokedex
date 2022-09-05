package com.mx.modyo.model;

import java.util.ArrayList;

import lombok.Data;

public @Data class EvolvesTo {
	
	private ArrayList<EvolutionDetail> evolution_details;
    private ArrayList<EvolvesTo> evolves_to;
    private boolean is_baby;
    private Species species;

}
