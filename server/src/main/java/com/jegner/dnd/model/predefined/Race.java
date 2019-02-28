package com.jegner.dnd.model.predefined;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.jegner.dnd.model.Attack;
import com.jegner.dnd.model.Size;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;

@Data
@Entity
@Predefined
public class Race {

	@Id
	@GeneratedValue
	private Long id;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Trait> traits;
	@OneToMany
	private List<Attack> attacks;
	private Size size;
	private int startingSpeed;
	@OneToMany
	private List<Language> languages;
	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
	@ElementCollection
	private Map<AbilityScore, Integer> abilityScoreIncreases;

	public Race() {
		abilityScoreIncreases = new HashMap<>();
	}

	@JsonSetter("abilityScoreIncreases")
	public void setAbilityScoreIncreasesFromString(Map<String, Integer> abilityScoreMap) {

		for (Entry<String, Integer> entry : abilityScoreMap.entrySet()) {
			AbilityScore abilityIncrease = AbilityScore.findAbilityScoreByName(entry.getKey());
			int amount = entry.getValue();
			this.getAbilityScoreIncreases().put(abilityIncrease, amount);
		}
	}
}
