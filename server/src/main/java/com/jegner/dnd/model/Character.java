package com.jegner.dnd.model;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.item.Inventory;
import com.jegner.dnd.model.item.Item;
import com.jegner.dnd.model.item.Money;
import com.jegner.dnd.model.magic.CharacterSpellbook;
import com.jegner.dnd.model.modify.CharacterModifierSystem;
import com.jegner.dnd.model.modify.Modify;
import com.jegner.dnd.model.predefined.Background;
import com.jegner.dnd.model.predefined.Classs;
import com.jegner.dnd.model.predefined.Condition;
import com.jegner.dnd.model.predefined.Feat;
import com.jegner.dnd.model.predefined.Language;
import com.jegner.dnd.model.predefined.Race;
import com.jegner.dnd.model.predefined.Skill;
import com.jegner.dnd.utility.GameEntity;

import lombok.Data;

@Data
@Entity
public class Character {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
	private String Player;
	private boolean isMale;
	private int weightInLbs;
	private int heightInInches;
	@OneToOne
	private Classs charClass;
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
	@OneToOne
	private CharacterModifierSystem modSys;

	public Character() {
		modSys = new CharacterModifierSystem();
		charAbility = new CharacterAbility();
	}

	public int getAC() {
		return modSys.getCharacterAC();
	}

	public void addItem(Item item) {
		inventory.add(item);
	}

	public void equip(Item item) {
		inventory.equip(item);
		Modify itemModify = item.getGameEntity().getModify();
		if (itemModify != null) {
			modSys.addModify(itemModify);
		}
	}

	public void addAbilityScore(AbilityScore abilityScore, int value) {
		charAbility.getAbilityScores().put(abilityScore, value);
		modSys.addModify(abilityScore.getGameEntity().getModify());
	}

}
