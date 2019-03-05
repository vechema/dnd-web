package com.jegner.dnd.utility;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class DiceRollTest {

	@Test
	public void rollTester() {
		DiceRoll roll = new DiceRoll();
		roll.setBonus(0);
		roll.setNumDie(2);
		roll.setSidesOnDie(3);

		Map<Integer, Integer> frequency = new HashMap<>();

		for (int i = 0; i < 500; i++) {
			int rollResult = roll.getRoll();
			Integer previous = frequency.get(rollResult);
			if (previous == null) {
				frequency.put(rollResult, 1);
			} else {
				frequency.put(rollResult, previous + 1);
			}
		}

		System.out.println(frequency);
	}

}
