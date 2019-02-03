package com.jegner.dnd.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Level {
	private int proficiencyBonus;
	private List<Feature> features;
	private Map<Integer, Integer> spellLevelToSlots;
}
