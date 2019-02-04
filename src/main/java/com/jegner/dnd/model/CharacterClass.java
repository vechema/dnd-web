package com.jegner.dnd.model;

import java.util.List;

import com.jegner.dnd.model.item.Item;
import com.jegner.dnd.model.item.Money;
import com.jegner.dnd.model.magic.Spell;
import com.jegner.dnd.utility.GameEntity;

import lombok.Data;

@Data
public class CharacterClass {

	private GameEntity entity;
	private int hitDice;
	private List<AbilityScore> favoredAbilities;
	private Proficiency proficiencies;
	private List<Item> startingItems;
	private LevelingTable levelingTable;
	private Money startingMoney;
	private List<Spell> classSpells;
	private AbilityScore spellcastingAbility;

	private static List<CharacterClass> characterClasses;

}
