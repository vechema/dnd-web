package com.jegner.dnd.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.item.Item;
import com.jegner.dnd.model.item.Money;
import com.jegner.dnd.model.magic.Spell;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Prefetched;

import lombok.Data;

@Data
@Entity
@Prefetched
public class CharacterClass {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private GameEntity entity;
	private int hitDice;
	@ElementCollection
	private List<AbilityScore> favoredAbilities;
	@OneToOne
	private Proficiency proficiencies;
	@OneToMany
	private List<Item> startingItems;
	@OneToOne
	private LevelingTable levelingTable;
	@OneToOne
	private Money startingMoney;
	@OneToMany
	private List<Spell> classSpells;
	private AbilityScore spellcastingAbility;

}
