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
import com.jegner.dnd.model.item.Item;
import com.jegner.dnd.model.item.Money;
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
	@ElementCollection
	private List<Item> startingItems;
	@OneToOne(cascade = CascadeType.ALL)
	private LevelingTable levelingTable;
	@OneToOne
	private Money startingMoney;
	@OneToMany
	private List<Spell> classSpells;
	@OneToOne
	private AbilityScore spellcastingAbility;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Choice> choices;

	public Classs() {
		savingThrowAbilityScores = new ArrayList<>();
		startingItems = new ArrayList<>();
	}

	@JsonSetter("savingThrowAbilityScores")
	public void setSavingThrowAbilityScoresFromString(List<String> savingThrowsString) {
		savingThrowsString.stream().forEach(abilityScoreString -> savingThrowAbilityScores
				.add(AbilityScore.findAbilityScoreByName(abilityScoreString)));
	}

	@JsonSetter("startingItems")
	public void setStartingItemsFromString(List<String> startingItemsString) {
		startingItemsString.stream()
				.forEach(startingItemString -> startingItems.add(Item.findItemByName(startingItemString)));
	}
}
