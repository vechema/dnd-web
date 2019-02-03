package com.jegner.dnd.model;

import java.util.List;
import java.util.Map;

import com.jegner.dnd.model.item.Inventory;
import com.jegner.dnd.model.item.Money;
import com.jegner.dnd.model.magic.CharacterSpellbook;
import com.jegner.dnd.utility.GameEntity;

import lombok.Data;

@Data
public class Character {
	private GameEntity entity;
	private String Player;
	private boolean isMale;
	private CharacterClass charClass;
	private Race race;
	private Background background;
	private Alignment alignment;
	private Experience experience;
	private CharacterAbility charAbility;
	private CharacterSpellbook charSpell;
	private Inventory inventory;
	private Money money;
	private Life life;
	private int speed;
	private Map<Skill, Integer> skills;
	private List<Language> languages;
	private Proficiency proficiencies;
	private List<Feat> feats;

}
