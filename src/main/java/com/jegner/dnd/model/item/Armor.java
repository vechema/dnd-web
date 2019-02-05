package com.jegner.dnd.model.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.utility.Duration;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Prefetched;

import lombok.Data;

@Entity
@Data
@Prefetched
public class Armor extends Item {

	@GeneratedValue
	@Id
	private Long id;

	@OneToOne
	private GameEntity entity;
	private int AC;
	private boolean isStealth;
	private EquipmentType equipmentType;
	@OneToOne
	private Duration donTime;
	@OneToOne
	private Duration doffTime;
}
