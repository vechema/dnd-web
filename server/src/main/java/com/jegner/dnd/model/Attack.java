package com.jegner.dnd.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.item.DamageType;
import com.jegner.dnd.utility.DiceRoll;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;

@Entity
@Data
@Predefined
public class Attack {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;

	@OneToOne(cascade = CascadeType.ALL)
	private DiceRoll damage;
	private DamageType damageType;
	private int normalRange;
	private int maxRange;
}
