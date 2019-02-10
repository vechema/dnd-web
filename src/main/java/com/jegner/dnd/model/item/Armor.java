package com.jegner.dnd.model.item;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.utility.Duration;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.PreDefined;

import lombok.Data;

@Entity
@Data
@PreDefined
public class Armor extends Item {

	@GeneratedValue
	@Id
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
	private int ac;
	private boolean isStealth;
	private EquipmentType equipmentType;
	@OneToOne(cascade = CascadeType.ALL)
	private Duration donTime;
	@OneToOne(cascade = CascadeType.ALL)
	private Duration doffTime;
}
