package com.jegner.dnd.model.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.Data;

@Data
@Entity
public class InventoryItem {

	@Id
	@GeneratedValue
	private long id;
	@OneToOne
	private Item item;
	private int quantity;
	private String notes;
	private boolean isEquipped;

	@JsonSetter("item")
	public void setItemFromString(String itemStrings) {
		item = Item.findItemByName(itemStrings);
	}
}
