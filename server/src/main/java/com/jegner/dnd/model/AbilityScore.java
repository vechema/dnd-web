package com.jegner.dnd.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.jegner.dnd.utility.GameEntity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class AbilityScore {

	@Id
	@GeneratedValue
	private long id;

	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;

	@Transient
	@Getter
	@Setter
	private static List<AbilityScore> abilityScores;

	public static AbilityScore findAbilityScoreByName(String name) {
		return abilityScores.stream()
				.filter(abilityScore -> abilityScore.getGameEntity().getName().equalsIgnoreCase(name)).findFirst()
				.get();
	}
}
