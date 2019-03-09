package com.jegner.dnd.model.predefined;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
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

	private int level;
	@OneToOne(cascade = CascadeType.ALL)
	private Modify proficiencyBonus;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Feature> features;
	@ElementCollection
	private Map<Integer, Integer> spellLevelToSlots;
	@ElementCollection
	// Used for things not common across all classes like rage, ki, sneak attack
	private Map<String, String> extraLevelColumn;
}
