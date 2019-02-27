package com.jegner.dnd.model.predefined;

import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.modify.Modify;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;

@Data
@Entity
@Predefined
public class Level {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private Modify proficiencyBonus;
	private int level;
	@OneToMany
	private List<Feature> features;
	@ElementCollection
	private Map<Integer, Integer> spellLevelToSlots;
}
