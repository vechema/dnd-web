package com.jegner.dnd.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.jegner.dnd.model.item.EquipmentType;
import com.jegner.dnd.model.item.Item;
import com.jegner.dnd.model.predefined.AbilityScore;
import com.jegner.dnd.model.predefined.Skill;

import lombok.Data;

@Entity
@Data
public class Proficiency {

	@Id
	@GeneratedValue
	private long id;
	@OneToMany
	private List<Item> items; // tools, weapon
	@OneToMany
	private List<AbilityScore> abilityScores;
	@OneToMany
	private List<Skill> skills;
	@ElementCollection
	private List<EquipmentType> combatProficiencies;

	public Proficiency() {
		items = new ArrayList<>();
	}

	@JsonSetter("items")
	public void setItemsFromString(List<String> itemStrings) {
		itemStrings.stream().forEach(itemString -> items.add(Item.findItemByName(itemString)));
	}
}
