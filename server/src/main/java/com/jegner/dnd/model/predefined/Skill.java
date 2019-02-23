package com.jegner.dnd.model.predefined;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jegner.dnd.database.SkillDeserializer;
import com.jegner.dnd.model.AbilityScore;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.PreDefined;

import lombok.Data;

@Data
@Entity
@PreDefined
@JsonDeserialize(using = SkillDeserializer.class)
public class Skill {

	@Id
	@GeneratedValue
	private long id;

	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
	@OneToOne
	private AbilityScore abilityModifier;
}
