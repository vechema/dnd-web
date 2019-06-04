package com.jegner.dnd.model.item;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Predefined
public class WeaponProperty {

	@GeneratedValue
	@Id
	private Long id;
	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;

	@Transient
	@Getter
	@Setter
	private static List<WeaponProperty> weaponProperties;

	public static WeaponProperty findWeaponPropertyByName(String name) {
		return weaponProperties.stream()
				.filter(weaponProperty -> weaponProperty.getGameEntity().getName().equalsIgnoreCase(name)).findFirst()
				.get();
	}

}
