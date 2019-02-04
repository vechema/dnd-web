package com.jegner.dnd.model.magic;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Spellbook {

	private List<Spell> knownSpells;
	private List<Spell> preparedSpells;
	private Map<Integer, Integer> spellLevelToSlots;
	private Map<Integer, Integer> spellLevelToSlotsUsed;

}
