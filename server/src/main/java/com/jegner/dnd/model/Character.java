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
import com.jegner.dnd.model.modify.CharacterModifySystem;
import com.jegner.dnd.model.modify.Modify;
import com.jegner.dnd.model.modify.ModifyField;
import com.jegner.dnd.model.predefined.AbilityScore;
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
	private int currentLevel;
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
	private CharacterModifySystem modSys;

	public Character() {
		modSys = new CharacterModifySystem();
		charAbility = new CharacterAbility();
		inventory = new Inventory();
		gameEntity = new GameEntity();
		initLevel(1);
		initHP();
	}

	private void initLevel(int level) {
		currentLevel = level;
		Modify levelModify = new Modify();
		levelModify.setBase(level);
		levelModify.setModifyField(ModifyField.LEVEL);
		this.gameEntity.addModify(levelModify);
		modSys.addModify(levelModify);

	}

	// Changed later to use Race
	private void initHP() {
		Modify hpModify = new Modify();
		hpModify.setBase(0);
		hpModify.setModifyField(ModifyField.HP);
		hpModify.addFieldThatModifyMe(ModifyField.HP_MOD);
		this.gameEntity.addModify(hpModify);
		modSys.addModify(hpModify);
	}

	public int getAC() {
		return modSys.getCharacterAC();
	}

	public int getInitiative() {
		return modSys.getCharacterInitiative();
	}

	public int getAttackHit() {
		return modSys.getCharacterAttackHit();
	}

	public int getHP() {
		return modSys.getCharacterHP();
	}

	public void addItem(Item item) {
		inventory.add(item);
	}

	public void equip(Item item) {
		inventory.equip(item);
		modSys.addModifys(item.getGameEntity().getModifys());
	}

	public void addAbilityScore(AbilityScore abilityScore, int value) {
		charAbility.getAbilityScores().put(abilityScore, value);
		modSys.addModifys(abilityScore.getGameEntity().getModifys());
	}

	public void addModify(Modify modify) {
		modSys.addModify(modify);
	}

	public void setCurrentLevel(int level) {
		this.currentLevel = level;
		this.gameEntity.getModifys().stream().filter(modify -> modify.getModifyField().equals(ModifyField.LEVEL))
				.findFirst().get().setBase(level);

		// TODO Need to remove old level modifys
		modSys.addModify(this.charClass.getLevelingTable().getLevel(currentLevel).getProficiencyBonus());
	}

}
