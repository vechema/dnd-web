package com.jegner.dnd.model.choice;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.Attack;
import com.jegner.dnd.model.Proficiency;
import com.jegner.dnd.model.magic.Spell;
import com.jegner.dnd.model.predefined.Language;
import com.jegner.dnd.model.predefined.Skill;
import com.jegner.dnd.model.predefined.SubRace;
import com.jegner.dnd.model.predefined.Trait;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;

@Data
@Entity
@Predefined
public class Option {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;

	@OneToOne(cascade = CascadeType.ALL)
	private Trait trait;
	@OneToOne(cascade = CascadeType.ALL)
	private Attack attack;
	@OneToOne(cascade = CascadeType.ALL)
	private Spell spell;
	@OneToOne(cascade = CascadeType.ALL)
	private Proficiency proficiency;
	@OneToOne
	private Skill skill;
	@OneToOne
	private Language language;
	@OneToOne
	private SubRace subRace;
}
