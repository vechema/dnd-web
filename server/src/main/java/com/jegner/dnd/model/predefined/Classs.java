package com.jegner.dnd.model.predefined;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.jegner.dnd.model.Proficiency;
import com.jegner.dnd.model.choice.Choice;
import com.jegner.dnd.model.magic.Spell;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;

@Data
@Entity
@Predefined
public class Classs {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
	private int hitDice;
	@OneToMany
	private List<AbilityScore> savingThrowAbilityScores;
	@OneToOne(cascade = CascadeType.ALL)
	private Proficiency proficiency;
	@OneToOne
	private AbilityScore spellcastingAbility;
	@OneToOne(cascade = CascadeType.ALL)
	private LevelingTable levelingTable;
	@OneToMany
	private List<Spell> classSpells;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Choice> choices;
	/**
	 * This list represents Primal Path, Ranger Archetype, Divine Domain, etc. For
	 * Barbarian, it would be Primal Path and the list would consistent of the
	 * different paths. Sadly, I have not figured out where to put the name "Primal
	 * Path"
	 */
	@OneToMany(cascade = CascadeType.ALL)
	private List<ClassSpecialty> classSpecialtys;
	/**
	 * The levels at which you can increase one ability score of your choice by 2,
	 * or you can increase two ability scores of your choice by 1. You can't
	 * increase an ability score above 20 using this feature.
	 */
	@ElementCollection
	private List<Integer> levelsOfImprovement;

	public Classs() {
		savingThrowAbilityScores = new ArrayList<>();
	}

	@JsonSetter("savingThrowAbilityScores")
	public void setSavingThrowAbilityScoresFromString(List<String> savingThrowsString) {
		savingThrowsString.stream().forEach(abilityScoreString -> savingThrowAbilityScores
				.add(AbilityScore.findAbilityScoreByName(abilityScoreString)));
	}
}
