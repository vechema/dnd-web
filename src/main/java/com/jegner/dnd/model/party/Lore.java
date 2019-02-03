package com.jegner.dnd.model.party;

import java.util.List;

import lombok.Data;

@Data
public class Lore {
	private Party party;
	private List<Location> places;
	private List<Quest> quests;
	private List<NPC> npcs;
	private String notes;
}
