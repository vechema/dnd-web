package com.jegner.dnd.model.modify;

public enum ModifyField {
	HP,
	CHARACTER_AC,
	ARMOR_AC,
	ABILITY_SCORE,
	INITIATIVE,
	STRENGTH,
	STRENGTH_MOD,
	DEXTERITY,
	DEXTERITY_MOD,
	CONSTITUTION,
	CONSTITUTION_MOD,
	INTELLIGENCE,
	INTELLIGENCE_MOD,
	WISDOM,
	WISDOM_MOD,
	CHARISMA,
	CHARISMA_MOD,
	ATTACK_HIT, // For proficiency, magic, and attack_hit_mod
	ATTACK_HIT_MOD // To figure out if use strength of dex mod due to finesse or Monk martial arts
}
