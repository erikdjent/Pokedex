package com.mx.modyo.model;

import lombok.Data;

public @Data class EvolutionDetail {
	
    private Object gender;
    private Object held_item;
    private Object item;
    private Object known_move;
    private Object known_move_type;
    private Object location;
    private Object min_affection;
    private Object min_beauty;
    private Object min_happiness;
    private int min_level;
    private boolean needs_overworld_rain;
    private Object party_species;
    private Object party_type;
    private Object relative_physical_stats;
    private String time_of_day;
    private Object trade_species;
    private Trigger trigger;
    private boolean turn_upside_down;

}
