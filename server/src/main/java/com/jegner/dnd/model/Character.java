package com.jegner.dnd.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.util.StringUtils;

import com.jegner.dnd.model.item.Inventory;
import com.jegner.dnd.model.item.Item;
import com.jegner.dnd.model.item.Money;
import com.jegner.dnd.model.magic.CharacterSpellbook;
import com.jegner.dnd.model.modify.CharacterModifySystem;
import com.jegner.dnd.model.modify.Modify;
import com.jegner.dnd.model.modify.ModifyField;
import com.jegner.dnd.model.modify.ModifyOperation;
import com.jegner.dnd.model.predefined.AbilityScore;
import com.jegner.dnd.model.predefined.Background;
import com.jegner.dnd.model.predefined.Classs;
import com.jegner.dnd.model.predefined.Condition;
import com.jegner.dnd.model.predefined.Feat;
import com.jegner.dnd.model.predefined.Feature;
import com.jegner.dnd.model.predefined.Language;
import com.jegner.dnd.model.predefined.Level;
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
	private boolean isMale;
	private int weightInLbs;
	private int heightInInches;
	private int currentLevel;
	private int currentProficiencyBonus;
	@OneToOne
	private Classs classs;
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
	@ElementCollection
	private Map<Feature, Boolean> featuresToActive;
	@ElementCollection // Used for ki, rage, etc
	private Map<String, Integer> featurePoolToAmount;

	public Character() {
		modSys = new CharacterModifySystem();
		charAbility = new CharacterAbility();
		inventory = new Inventory();
		gameEntity = new GameEntity();
		featuresToActive = new HashMap<>();
		featurePoolToAmount = new HashMap<>();
		initLevel(1);
		initHP();
		initAttackAndDefense();
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

	private void initAttackAndDefense() {
		Modify attackHitModify = new Modify();
		attackHitModify.setModifyField(ModifyField.ATTACK_HIT);
		modSys.addModify(attackHitModify);

		Modify damageModify = new Modify();
		damageModify.setModifyField(ModifyField.DAMAGE);
		modSys.addModify(damageModify);

		Modify unarmoredACModify = new Modify();
		unarmoredACModify.setModifyField(ModifyField.UNARMORED_AC);
		unarmoredACModify.setFieldIModify(ModifyField.CHARACTER_AC);
		unarmoredACModify.setBase(10);
		unarmoredACModify.addFieldThatModifyMe(ModifyField.DEXTERITY_MOD);
		modSys.addModify(unarmoredACModify);

		Modify characterACModify = new Modify();
		characterACModify.setModifyField(ModifyField.CHARACTER_AC);
		characterACModify.setModifyOperation(ModifyOperation.MAX);
		modSys.addModify(characterACModify);
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

	public int getDamage() {
		return modSys.getCharacterDamage();
	}

	public int getHP() {
		return modSys.getCharacterHP();
	}

	public int getFeatureUsesLeft(Feature feature) {
		return featurePoolToAmount.get(feature.getResourcePool());
	}

	public void addItem(Item item) {
		inventory.add(item);
	}

	public void equip(Item item) {
		inventory.equip(item);
		modSys.addModifys(item.getGameEntity().getModifys());
	}

	public void unequip(Item item) {
		inventory.unequip(item);
		modSys.removeModifys(item.getGameEntity().getModifys());
	}

	public boolean isArmorEquipped() {
		return inventory.isArmorEquipped();
	}

	public Object getEquippedArmor() {
		if (isArmorEquipped()) {
			return inventory.getEquippedArmor();
		}
		return null;
	}

	public boolean isWeaponEquipped() {
		return inventory.isWeaponEquipped();
	}

	public Object getWeapon() {
		if (isWeaponEquipped()) {
			return inventory.getEquippedWeapon();
		}
		return null;
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
		modSys.addModify(this.classs.getLevelingTable().getLevel(currentLevel).getProficiencyBonus());
	}

	public void setName(String name) {
		this.gameEntity.setName(name);
	}

	public void setClasssByName(String name) {
		this.classs = Classs.findClassByName(name);
	}

	public void setRaceByName(String name) {
		this.race = Race.findRaceByName(name);
	}

	/**
	 * Need to copy over things like proficiencies, features, modifiers, etc
	 * 
	 * @param classs
	 */
	public void setClasss(Classs classs) {
		this.classs = classs;
		// Loop through each level
		for (int i = 1; i <= this.currentLevel; i++) {
			Level levelI = classs.getLevelingTable().getLevel(i);
			List<Feature> newFeatures = levelI.getFeatures();
			// Add any feature modifys and any pools
			for (Feature newFeature : newFeatures) {
				featuresToActive.put(newFeature, false);
				if (newFeature.isPassive()) {
					modSys.addModifys(newFeature.getGameEntity().getModifys());
				}
				String featurePool = newFeature.getResourcePool();
				if (!StringUtils.isEmpty(featurePool)) {
					featurePoolToAmount.put(featurePool, 0);
				}
			}
			// Take care of extraLevelColumns
			for (GameEntity extraLevelColumn : levelI.getExtraLevelColumns()) {
				modSys.addModifys(extraLevelColumn.getModifys());
				if (featurePoolToAmount.containsKey(extraLevelColumn.getName())) {
					featurePoolToAmount.put(extraLevelColumn.getName(), extraLevelColumn.getModifys().get(0).getBase());
				}
			}
		}
	}

	public boolean useFeature(Feature feature) {
		int featureUses = getFeatureUsesLeft(feature);
		// if no uses left OR it's already active
		if (featureUses <= 0 || featuresToActive.get(feature)) {
			return false;
		}
		featurePoolToAmount.put(feature.getResourcePool(), featureUses - 1);
		featuresToActive.put(feature, true);
		modSys.addModifys(feature.getGameEntity().getModifys());

		// Add feature mods
		return true;
	}

	public boolean turnOffFeature(Feature feature) {
		// The feature isn't actually in use
		if (!featuresToActive.get(feature)) {
			return false;
		}

		featuresToActive.put(feature, false);
		modSys.removeModifys(feature.getGameEntity().getModifys());
		return true;
	}
}
