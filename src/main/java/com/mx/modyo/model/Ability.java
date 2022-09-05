package com.mx.modyo.model;


import lombok.Data;

public @Data class Ability {

	private boolean is_hidden;
	private int slot;
	private Ability2 ability;
}
