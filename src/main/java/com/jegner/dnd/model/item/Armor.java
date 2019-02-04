package com.jegner.dnd.model.item;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.jegner.dnd.utility.Duration;
import com.jegner.dnd.utility.GameEntity;

import lombok.Data;

@Data
public class Armor extends Item {

	private GameEntity entity;
	private int AC;
	private boolean isStealth;
	private EquipmentType equipmentType;

	private Duration donTime;
	private Duration doffTime;

	private static List<Armor> armors;
}
