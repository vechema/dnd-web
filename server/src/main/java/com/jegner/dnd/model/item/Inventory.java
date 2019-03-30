package com.jegner.dnd.model.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Inventory {

	@Id
	@GeneratedValue
	private Long id;
	@OneToMany
	private List<InventoryItem> items;

	public Inventory() {
		items = new ArrayList<>();
	}

	public void add(Item item) {
		InventoryItem newItem = new InventoryItem();
		newItem.setEquipped(false);
		newItem.setItem(item);
		newItem.setQuantity(1);
		items.add(newItem);
	}

	public void equip(Item item) {
		if (item instanceof Armor) {
			if (isArmorEquipped()) {
				unequip(getEquippedArmor());
			}
		} else if (item instanceof Weapon) {
			if (isWeaponEquipped()) {
				unequip(getEquippedWeapon());
			}
		}
		InventoryItem invItem = findInventoryItem(item);
		invItem.setEquipped(true);
	}

	public void unequip(Item item) {
		InventoryItem invItem = findInventoryItem(item);
		invItem.setEquipped(false);
	}

	public InventoryItem findInventoryItem(Item item) {
		return items.stream().filter(inventoryItem -> inventoryItem.getItem().getGameEntity().getName()
				.equalsIgnoreCase(item.getGameEntity().getName())).findFirst().get();
	}

	public boolean isArmorEquipped() {
		return items.stream().anyMatch(item -> item.getItem() instanceof Armor && item.isEquipped());
	}

	public Armor getEquippedArmor() {
		if (!isArmorEquipped()) {
			return null;
		}
		return (Armor) items.stream().filter(item -> item.getItem() instanceof Armor && item.isEquipped()).findFirst()
				.get().getItem();
	}

	public boolean isWeaponEquipped() {
		return items.stream().anyMatch(item -> item.getItem() instanceof Weapon && item.isEquipped());
	}

	public Weapon getEquippedWeapon() {
		if (!isWeaponEquipped()) {
			return null;
		}
		return (Weapon) items.stream().filter(item -> item.getItem() instanceof Weapon && item.isEquipped()).findFirst()
				.get().getItem();
	}
}
