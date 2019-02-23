package com.jegner.dnd.model;

import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class CharacterAbility {

	@Id
	@GeneratedValue
	private Long id;

	@ElementCollection
	private List<AbilityScore> abilityScores;
	public static final int STARTING_SCORE_LOW = 8;
	public static final int STARTING_SCORE_HIGH = 15;

	public static int costToBuy(int desiredScore) {
		if (desiredScore < STARTING_SCORE_LOW || desiredScore > STARTING_SCORE_HIGH) {
			throw new IllegalArgumentException("Desired score of " + desiredScore + " is outside range "
					+ STARTING_SCORE_LOW + " to " + STARTING_SCORE_HIGH);
		}

		if (desiredScore <= 13) {
			return desiredScore - 8;
		} else if (desiredScore == 14) {
			return 7;
		} else { // must be 15
			return 9;
		}
	}

}
