package com.jegner.dnd.model;

import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jegner.dnd.utility.Prefetched;

import lombok.Data;

@Data
@Entity
@Prefetched
public class Level {

	@Id
	@GeneratedValue
	private Long id;

	private int proficiencyBonus;
	private int level;
	@OneToOne
	private CharacterClass charClass;
	@OneToMany
	private List<Feature> features;
	@ElementCollection
	private Map<Integer, Integer> spellLevelToSlots;
}
