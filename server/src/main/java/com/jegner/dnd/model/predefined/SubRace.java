package com.jegner.dnd.model.predefined;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.jegner.dnd.model.Proficiency;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;

@Data
@Entity
@Predefined
public class SubRace {

	@Id
	@GeneratedValue
	private Long id;
	@ElementCollection
	private Map<AbilityScore, Integer> abilityScoreIncreases;
	@OneToMany
	private List<Trait> traits;
	@OneToMany
	private List<Proficiency> proficiencies;

	@OneToOne
	private GameEntity gameEntity;

	@JsonSetter("abilityScoreIncreases")
	public void setAbilityScoreIncreasesFromString(Map<String, Integer> abilityScoreMap) {

		for (Entry<String, Integer> entry : abilityScoreMap.entrySet()) {
			AbilityScore abilityIncrease = AbilityScore.findAbilityScoreByName(entry.getKey());
			int amount = entry.getValue();
			this.getAbilityScoreIncreases().put(abilityIncrease, amount);
		}
	}
}
