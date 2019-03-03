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

	public int getDamage() {
		return IntStream.range(0, this.numDie).map(i -> (int) (Math.random() * this.sidesOnDie) + 1 + this.bonus).sum();
	}

	@Override
	public String toString() {
		return "" + numDie + "d" + sidesOnDie + " + " + bonus;
	}

}
