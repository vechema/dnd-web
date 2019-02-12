package com.jegner.dnd.model.item;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.PreDefined;

import lombok.Data;

@Data
@Entity
@PreDefined
public class Item {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
	private double valueInGold;
	private double weightInLbs;
}
