package com.jegner.dnd.model.party;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Lore {
	@Id
	@GeneratedValue
	private long id;
	@OneToOne
	private Party party;
	@OneToMany
	private List<Location> places;
	@OneToMany
	private List<Quest> quests;
	@OneToMany
	private List<NPC> npcs;
	private String notes;
}
