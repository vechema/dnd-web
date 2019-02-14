package com.jegner.dnd.model.item;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
}
