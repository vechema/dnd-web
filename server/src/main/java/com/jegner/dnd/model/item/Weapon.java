package com.jegner.dnd.model.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.jegner.dnd.model.Attack;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

@Data
@Entity
@Predefined
public class Weapon extends Item {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private Attack attack;
	@ManyToMany
	private List<WeaponProperty> properties;
	private EquipmentType equipmentType;
	private boolean isMelee;

	@Transient
	@Getter
	@Setter
	private static List<Weapon> weapons;

	public Weapon() {
		properties = new ArrayList<>();
	}

	@JsonSetter("properties")
	@Tolerate
	public void setWeaponProperties(List<String> propertiesString) {
		propertiesString.stream().forEach(
				weaponPropertyString -> properties.add(WeaponProperty.findWeaponPropertyByName(weaponPropertyString)));
	}

	public static Weapon findWeaponByName(String name) {
		return weapons.stream()
				.filter(weaponProperty -> weaponProperty.getGameEntity().getName().equalsIgnoreCase(name)).findFirst()
				.get();
	}
}
