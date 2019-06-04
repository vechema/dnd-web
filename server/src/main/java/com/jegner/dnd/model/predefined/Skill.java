package com.jegner.dnd.model.predefined;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

@Data
@Entity
@Predefined
public class Skill {

	@Id
	@GeneratedValue
	private long id;

	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
	@OneToOne
	private AbilityScore abilityModifier;

	@Transient
	@Getter
	@Setter
	private static List<Skill> skills;

	@JsonSetter
	@Tolerate
	public void setAbilityModifier(String abilityModifier) {
		this.abilityModifier = AbilityScore.findAbilityScoreByName(abilityModifier);
	}

	public static Skill findSkillByName(String name) {
		return skills.stream().filter(skill -> skill.getGameEntity().getName().equalsIgnoreCase(name)).findFirst()
				.get();
	}
}
