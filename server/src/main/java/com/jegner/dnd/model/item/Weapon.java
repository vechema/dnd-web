package com.jegner.dnd.model.item;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jegner.dnd.utility.DiceRoll;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;

@Data
@Entity
@Predefined
public class Weapon extends Item {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private DiceRoll damage;
	private DamageType damageType;
	private int normalRange;
	private int maxRange;
	@OneToMany
	private List<WeaponProperty> properties;
	private EquipmentType equipmentType;
}
