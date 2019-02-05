package com.jegner.dnd.model.magic;

import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Spellbook {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany
	private List<Spell> knownSpells;
	@OneToMany
	private List<Spell> preparedSpells;
	@ElementCollection
	private Map<Integer, Integer> spellLevelToSlots;
	@ElementCollection
	private Map<Integer, Integer> spellLevelToSlotsUsed;

}
