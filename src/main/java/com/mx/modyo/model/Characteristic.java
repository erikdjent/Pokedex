package com.mx.modyo.model;

import java.util.ArrayList;

import lombok.Data;

public @Data class Characteristic {
	
	private ArrayList<Description> descriptions;
    private HighestStat highest_stat;

}
