package com.jegner.dnd.utility;

import java.util.stream.IntStream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class DiceRoll {

	@Id
	@GeneratedValue
	private Long id;
	private int numDie;
	private int sidesOnDie;
	private int bonus;
	private int multiplier;

	public DiceRoll() {
		numDie = 1;
		sidesOnDie = 1;
		bonus = 0;
		multiplier = 1;
	}

	public int getRoll() {
		return IntStream.range(0, this.numDie)
				.map(i -> ((int) (Math.random() * this.sidesOnDie) + 1 + this.bonus) * this.multiplier).sum();
	}

	@Override
	public String toString() {
		return "" + numDie + "d" + sidesOnDie + " + " + bonus;
	}

}
