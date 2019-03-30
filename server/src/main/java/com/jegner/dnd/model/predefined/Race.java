package com.jegner.dnd.model.predefined;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.jegner.dnd.model.Attack;
import com.jegner.dnd.model.Proficiency;
import com.jegner.dnd.model.Size;
import com.jegner.dnd.model.choice.Choice;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Predefined
public class Race {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Trait> traits;
	@OneToMany
	private List<Attack> attacks;
	private Size size;
	private int startingSpeed;
	@ManyToMany
	private List<Language> languages;
	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
	@ElementCollection
	private Map<AbilityScore, Integer> abilityScoreIncreases;
	@OneToOne(cascade = CascadeType.ALL)
	private Proficiency proficiency;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Choice> choices;

	@Transient
	@Getter
	@Setter
	private static List<Race> races = new ArrayList<>();

	public static Race findRaceByName(String name) {
		return races.stream().filter(race -> race.getGameEntity().getName().equalsIgnoreCase(name)).findFirst().get();
	}

	public Race() {
		abilityScoreIncreases = new HashMap<>();
		languages = new ArrayList<>();
		traits = new ArrayList<>();
	}

	@JsonSetter("abilityScoreIncreases")
	public void setAbilityScoreIncreasesFromString(Map<String, Integer> abilityScoreMap) {
		for (Entry<String, Integer> entry : abilityScoreMap.entrySet()) {
			AbilityScore abilityIncrease = AbilityScore.findAbilityScoreByName(entry.getKey());
			int amount = entry.getValue();
			this.getAbilityScoreIncreases().put(abilityIncrease, amount);
		}
	}

	@JsonSetter("traits")
	public void setTraits(List<Object> traits) {
		for (Object trait : traits) {
			if (trait instanceof String) {
				this.traits.add(Trait.findCommonTraitByName((String) trait));
			} else if (trait instanceof Trait) {
				this.traits.add((Trait) trait);
			}
		}
	}

	@JsonSetter("languages")
	public void setLanguagesFromString(List<String> languagesString) {
		languagesString.stream().forEach(languageString -> languages.add(Language.findLanguageByName(languageString)));
	}
}
