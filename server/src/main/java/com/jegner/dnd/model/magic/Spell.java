package com.jegner.dnd.model.magic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.jegner.dnd.model.item.Item;
import com.jegner.dnd.model.predefined.AbilityScore;
import com.jegner.dnd.utility.Duration;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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
	private int defaultSpellLevel;

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

	@Transient
	@Getter
	@Setter
	private static List<Spell> spells = new ArrayList<>();

	public static Spell findSpellByName(String name) {
		return spells.stream().filter(spell -> spell.getGameEntity().getName().equalsIgnoreCase(name)).findFirst()
				.get();
	}

	@JsonSetter
	@Tolerate
	public void setSave(String save) {
		this.save = AbilityScore.findAbilityScoreByName(save);
	}
}
