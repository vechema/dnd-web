package com.jegner.dnd.model.predefined;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.AbilityScore;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.PreDefined;

import lombok.Data;

@Data
@Entity
@PreDefined
public class Skill {

	@Id
	@GeneratedValue
	private long id;

	@OneToOne
	private GameEntity entity;
	private AbilityScore abilityModifier;
}
