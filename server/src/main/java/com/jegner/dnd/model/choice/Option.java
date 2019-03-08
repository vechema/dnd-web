package com.jegner.dnd.model.choice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.jegner.dnd.model.Attack;
import com.jegner.dnd.model.Proficiency;
import com.jegner.dnd.model.item.InventoryItem;
import com.jegner.dnd.model.magic.Spell;
import com.jegner.dnd.model.predefined.Language;
import com.jegner.dnd.model.predefined.SubRace;
import com.jegner.dnd.model.predefined.Trait;
import com.jegner.dnd.utility.DiceRoll;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;

@Data
@Entity
@Predefined
public class Option {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;

	@OneToOne(cascade = CascadeType.ALL)
	private Trait trait;
	@OneToOne(cascade = CascadeType.ALL)
	private Attack attack;
	@OneToOne(cascade = CascadeType.ALL)
	private Spell spell;
	@OneToOne(cascade = CascadeType.ALL)
	private Proficiency proficiency;
	@OneToOne
	private Language language;
	@OneToOne(cascade = CascadeType.ALL)
	private SubRace subRace;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<InventoryItem> items;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Choice> choices;
	@OneToOne(cascade = CascadeType.ALL)
	private DiceRoll startingGold;

	public Option() {
		items = new ArrayList<>();
	}

	@JsonSetter("items")
	public void setItemsFromString(List<String> itemsString) {
		itemsString.stream().forEach(itemString -> {
			InventoryItem invItem = new InventoryItem();
			invItem.setItemFromString(itemString);
			items.add(invItem);
		});
	}
}
