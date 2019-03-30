package com.jegner.dnd.model.magic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.jegner.dnd.model.Attack;
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

	// Doesn't change with levels
	private SchoolOfMagic schoolOfMagic;
	@ElementCollection
	private List<SpellComponent> spellComponents;
	@OneToMany
	private List<Item> components;
	private boolean isRitual;
	private boolean requiresConcentration;
	@OneToOne(cascade = CascadeType.ALL)
	private Duration castingTime;
	@OneToOne
	private AbilityScore save;
	private int defaultSpellLevel;

	// Might change with spell level used
	@OneToOne(cascade = CascadeType.ALL)
	private Duration duration;
	private String areaOfEffect;
	@OneToOne(cascade = CascadeType.ALL)
	private Attack attack;

	/**
	 * Some cantrips level when player hits levels 5, 11, 17. This means the damage
	 * increases by 1dX at each of those player levels. Ugh, and dragonborn breath
	 * weapon
	 */
	@ElementCollection
	private List<Integer> levelsWithPlayer;
	private boolean canBeUsedAtHigherSpellSlot;

	public Spell() {
		components = new ArrayList<>();
	}

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

	@JsonSetter("components")
	public void setComponentsFromString(List<String> itemsString) {
		itemsString.stream().forEach(itemString -> components.add(Item.findItemByName(itemString)));
	}
}
