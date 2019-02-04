package com.jegner.dnd.model;

import java.util.List;

import com.jegner.dnd.utility.GameEntity;

import lombok.Data;

@Data
public class Race {

	private List<Trait> traits;
	private Size size;
	private int startingSpeed;
	private List<Language> languages;
	private GameEntity entity;

	private static List<Race> races;
}
