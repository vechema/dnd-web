package com.jegner.dnd.model.magic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.predefined.AbilityScore;

import lombok.Data;

@Data
@Entity
public class CharacterSpellbook {

	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private Spellbook spellbook;
	@OneToOne
	private AbilityScore spellcastingAbility;
	private int spellSaveDC;
	private int spellAttackModifier;
}
