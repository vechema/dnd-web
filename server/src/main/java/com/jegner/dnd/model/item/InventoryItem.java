package com.jegner.dnd.model.item;

import java.util.NoSuchElementException;

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

	public InventoryItem() {
		quantity = 1;
		isEquipped = false;
	}

	@JsonSetter("item")
	public void setItemFromString(String itemString) {
		try {
			item = Item.findItemByName(itemString);
		} catch (NoSuchElementException e) {
			int quantity = Integer.parseInt(itemString.split(" ")[0]);
			this.setQuantity(quantity);
			String name = itemString.substring(itemString.indexOf(" ") + 1);
			this.setItemFromString(name);
		}
	}
}
