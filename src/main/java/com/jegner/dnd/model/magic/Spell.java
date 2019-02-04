package com.jegner.dnd.model.magic;

import java.util.List;

import com.jegner.dnd.model.AbilityScore;
import com.jegner.dnd.model.item.Item;
import com.jegner.dnd.utility.DiceRoll;
import com.jegner.dnd.utility.Duration;
import com.jegner.dnd.utility.GameEntity;

import lombok.Data;

@Data
public class Spell {

	private GameEntity entity;
	private int level;
	private Duration castingTime;
	private Duration duration;
	private int rangeInFeet;
	private String areaOfEffect;
	private List<Item> components;
	private DiceRoll damage;
	private SchoolOfMagic schoolOfMagic;
	private AbilityScore save;
	private boolean isRitual;
	private boolean requiresConcentration;

	private static List<Spell> spells;
}
