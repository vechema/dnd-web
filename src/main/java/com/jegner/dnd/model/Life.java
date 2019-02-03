package com.jegner.dnd.model;

import lombok.Data;

@Data
public class Life {
	private int hitPoints;
	private int tempHitPoints;
	private int hitDice;
	private int deathSavesSuccess;
	private int deathSavesFail;
}
