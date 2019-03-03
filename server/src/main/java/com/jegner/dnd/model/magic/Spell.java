package com.jegner.dnd.model.magic;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.jegner.dnd.model.item.Item;
import com.jegner.dnd.model.predefined.AbilityScore;
import com.jegner.dnd.utility.Duration;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;
import lombok.experimental.Tolerate;

@Entity
@Data
@Predefined
public class Spell {

	@GeneratedValue
	@Id
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
	@OneToMany(cascade = CascadeType.ALL)
	private Map<Integer, SpellLevel> spellLevels;

	// Doesn't change with levels
	private SchoolOfMagic schoolOfMagic;
	private boolean isRitual;
	private boolean requiresConcentration;
	@OneToMany
	private List<Item> components;
	@OneToOne(cascade = CascadeType.ALL)
	private Duration castingTime;
	@OneToOne
	private AbilityScore save;

	@JsonSetter
	@Tolerate
	public void setSave(String save) {
		this.save = AbilityScore.findAbilityScoreByName(save);
	}
}
