package com.jegner.dnd.model;

import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.item.Inventory;
import com.jegner.dnd.model.item.Money;
import com.jegner.dnd.model.magic.CharacterSpellbook;
import com.jegner.dnd.utility.GameEntity;

import lombok.Data;

@Data
@Entity
public class Character {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private GameEntity entity;
	private String Player;
	private boolean isMale;
	@OneToOne
	private CharacterClass charClass;
	@OneToOne
	private Race race;
	@OneToOne
	private Background background;
	private Alignment alignment;
	@OneToOne
	private Experience experience;
	@OneToOne
	private CharacterAbility charAbility;
	@OneToOne
	private CharacterSpellbook charSpell;
	@OneToOne
	private Inventory inventory;
	@OneToOne
	private Money money;
	@OneToOne
	private Life life;
	private int speed;
	@ElementCollection
	private Map<Skill, Integer> skills;
	@OneToMany
	private List<Language> languages;
	@OneToOne
	private Proficiency proficiencies;
	@OneToMany
	private List<Feat> feats;
	@OneToMany
	private List<Condition> conditions;

}
