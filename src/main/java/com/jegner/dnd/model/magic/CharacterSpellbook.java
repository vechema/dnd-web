package com.jegner.dnd.model.magic;

import com.jegner.dnd.model.AbilityScore;

import lombok.Data;

@Data
public class CharacterSpellbook {
	private Spellbook spellbook;
	private AbilityScore spellcastingAbility;
	private int spellSaveDC;
	private int spellAttackModifier;
}
