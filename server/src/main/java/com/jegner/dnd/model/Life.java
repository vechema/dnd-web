package com.jegner.dnd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Life {

	@Id
	@GeneratedValue
	private Long id;
	private int hitPoints;
	private int tempHitPoints;
	private int hitDice;
	private int deathSavesSuccess;
	private int deathSavesFail;
}
