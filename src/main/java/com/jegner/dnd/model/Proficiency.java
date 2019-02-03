package com.jegner.dnd.model;

import java.util.List;

import com.jegner.dnd.model.item.EquipmentType;
import com.jegner.dnd.model.item.Item;

public class Proficiency {
	private List<Item> items; // tools
	private List<AbilityScore> abilityScores;
	private List<Skill> skills;
	private List<EquipmentType> combatProficiencies;
}
