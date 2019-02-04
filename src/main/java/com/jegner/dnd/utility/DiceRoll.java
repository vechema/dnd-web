package com.jegner.dnd.utility;

import java.util.stream.IntStream;

import lombok.Data;

@Data
public class DiceRoll {

	private int numDie;
	private int sidesOnDie;
	private int bonus;

	public int getDamage() {

		return IntStream.range(0, this.numDie).map(i -> (int) (Math.random() * this.sidesOnDie) + 1 + this.bonus).sum();
	}

}
