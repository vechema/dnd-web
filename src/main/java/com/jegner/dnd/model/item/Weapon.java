package com.jegner.dnd.model.item;

import java.util.List;

import com.jegner.dnd.utility.DiceRoll;

import lombok.Data;

@Data
public class Weapon extends Item {
	private DiceRoll damage;
	private DamageType damageType;
	private List<WeaponProperty> properties;
	private EquipmentType equipmentType;

	private static List<Weapon> weapons;
}
