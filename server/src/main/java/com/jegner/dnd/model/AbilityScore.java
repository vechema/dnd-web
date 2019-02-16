package com.jegner.dnd.model;

import static com.jegner.dnd.model.modify.ModifierField.*;

import com.jegner.dnd.model.modify.ModifierField;

public enum AbilityScore {

	STRENGTH(10), DEXTERITY(10), CONSTITUTION(10), INTELLIGENCE(10), WISDOM(10), CHARISMA(10);

	private int abilityScore;

	private AbilityScore(int abilityScore) {
		this.abilityScore = abilityScore;
	}

	private int calculateModifier() {
		return this.abilityScore / 2 - 5;
	}

	public static ModifierField abilityScoreToModifier(AbilityScore abilityScore) {
		ModifierField modifierField = null;
		switch (abilityScore) {
		case CHARISMA:
			modifierField = CHARISMA_MOD;
			break;
		case CONSTITUTION:
			modifierField = CONSTITUTION_MOD;
			break;
		case DEXTERITY:
			modifierField = DEXTERITY_MOD;
			break;
		case INTELLIGENCE:
			modifierField = INTELLIGENCE_MOD;
			break;
		case STRENGTH:
			modifierField = STRENGTH_MOD;
			break;
		case WISDOM:
			modifierField = WISDOM_MOD;
			break;
		default:
			break;
		}
		return modifierField;
	}

	public static AbilityScore modifierToAbilityScore(ModifierField modField) {
		AbilityScore abilityScore = null;
		switch (modField) {
		case CHARISMA_MOD:
			abilityScore = CHARISMA;
			break;
		case CONSTITUTION_MOD:
			abilityScore = CONSTITUTION;
			break;
		case DEXTERITY_MOD:
			abilityScore = DEXTERITY;
			break;
		case INTELLIGENCE_MOD:
			abilityScore = INTELLIGENCE;
			break;
		case STRENGTH_MOD:
			abilityScore = STRENGTH;
			break;
		case WISDOM_MOD:
			abilityScore = WISDOM;
			break;
		default:
			break;
		}
		return abilityScore;
	}

}
