package com.jegner.dnd.model.item;

import java.util.List;

import com.jegner.dnd.model.Background;
import com.jegner.dnd.utility.GameEntity;

import lombok.Data;

@Data
public class WeaponProperty {
	private GameEntity entity;

	private static List<WeaponProperty> weaponProperties;
}
