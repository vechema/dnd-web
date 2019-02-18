package com.jegner.dnd.model.predefined;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.AbilityScore;
import com.jegner.dnd.model.Proficiency;
import com.jegner.dnd.model.item.Item;
import com.jegner.dnd.model.item.Money;
import com.jegner.dnd.model.magic.Spell;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.PreDefined;

import lombok.Data;

@Data
@Entity
@PreDefined
public class Classs {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
	private int hitDice;
	@OneToMany
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
	@OneToOne
	private AbilityScore spellcastingAbility;

}
