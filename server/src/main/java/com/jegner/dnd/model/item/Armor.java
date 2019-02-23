package com.jegner.dnd.model.item;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.modify.Modified;
import com.jegner.dnd.model.modify.Modifier;
import com.jegner.dnd.model.modify.ModifierField;
import com.jegner.dnd.utility.Duration;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.PreDefined;

import lombok.Data;
import lombok.Getter;
import lombok.AccessLevel;

@Entity
@Data
@PreDefined
public class Armor extends Item {

	@GeneratedValue
	@Id
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
	private boolean isStealth;
	private EquipmentType equipmentType;
	@OneToOne(cascade = CascadeType.ALL)
	private Duration donTime;
	@OneToOne(cascade = CascadeType.ALL)
	private Duration doffTime;
}
