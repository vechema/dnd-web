package com.jegner.dnd.model.magic;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.AbilityScore;
import com.jegner.dnd.model.item.Item;
import com.jegner.dnd.utility.DiceRoll;
import com.jegner.dnd.utility.Duration;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.PreDefined;

import lombok.Data;

@Entity
@Data
@PreDefined
public class Spell {

	@GeneratedValue
	@Id
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
	private int level;
	@OneToOne
	private Duration castingTime;
	@OneToOne
	private Duration duration;
	private int rangeInFeet;
	private String areaOfEffect;
	@OneToMany
	private List<Item> components;
	@OneToOne
	private DiceRoll damage;
	private SchoolOfMagic schoolOfMagic;
	@OneToOne
	private AbilityScore save;
	private boolean isRitual;
	private boolean requiresConcentration;
}
