package com.jegner.dnd.model.modify;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.jegner.dnd.model.Character;
import com.jegner.dnd.model.CharacterAbility;
import com.jegner.dnd.model.item.Armor;
import com.jegner.dnd.model.item.Weapon;
import com.jegner.dnd.model.predefined.AbilityScore;
import com.jegner.dnd.model.predefined.Classs;
import com.jegner.dnd.model.predefined.Level;
import com.jegner.dnd.model.predefined.LevelingTable;
import com.jegner.dnd.utility.GameEntity;

public class CharacterModifySystemTest {

	@Test
	public void armorModifiedByDexModTest() {

		// Create Character
		Character character = new Character();

		// Set AbilityScore
		AbilityScore dex = new AbilityScore();
		int dexScore = 18; // 4
		int dexModAmount = CharacterAbility.calculateModifier(dexScore);

		// Dex modifier
		Modify dexMod = new Modify();
		dexMod.setModifyField(ModifyField.DEXTERITY_MOD);
		dexMod.setBase(dexModAmount);

		GameEntity dexGameEntity = new GameEntity();
		dexGameEntity.setName("Dexterity");
		dexGameEntity.setModify(dexMod);
		dex.setGameEntity(dexGameEntity);

		// Give the dex to the player
		character.addAbilityScore(dex, dexScore);

		// Create Armor
		Armor armor = new Armor();

		// What is armor modified by? Dex in this case
		Modify armorModify = new Modify();
		armorModify.setBase(14);
		armorModify.setModifyField(ModifyField.ARMOR_AC);
		armorModify.setFieldIModify(ModifyField.CHARACTER_AC);

		Map<ModifyField, Integer> fieldsThatModifyMeMap = new HashMap<>();
		fieldsThatModifyMeMap.put(ModifyField.DEXTERITY_MOD, Integer.MAX_VALUE);
		armorModify.setFieldsThatModifyMe(fieldsThatModifyMeMap);

		// Wrap it up together
		GameEntity armorGameEntity = new GameEntity();
		armorGameEntity.setName("Ring Mail");
		armorGameEntity.setModify(armorModify);
		armor.setGameEntity(armorGameEntity);

		// Put armor in character inventory & equip
		character.addItem(armor);
		character.equip(armor);

		// Get Character AC
		int ac = character.getAC();

		int base = armor.getGameEntity().getModifys().stream().mapToInt(modify -> modify.getBase()).sum();
		assertThat(ac, is(base + dexModAmount));

		int dexModLimit = 2;
		fieldsThatModifyMeMap.put(ModifyField.DEXTERITY_MOD, dexModLimit);
		ac = character.getAC();
		assertThat(ac, is(base + dexModLimit));
	}

	@Test
	public void armorModifiedByDexOrStrengthModTest() {

		// Create Character
		Character character = new Character();

		// Set AbilityScore
		AbilityScore dex = new AbilityScore();
		int dexScore = 16; // 3
		int dexModAmount = CharacterAbility.calculateModifier(dexScore);
		AbilityScore strength = new AbilityScore();
		int strengthScore = 20; // 5
		int strengthModAmount = CharacterAbility.calculateModifier(strengthScore);

		// Dex modifier
		Modify dexMod = new Modify();
		dexMod.setModifyField(ModifyField.DEXTERITY_MOD);
		dexMod.setBase(dexModAmount);

		GameEntity dexGameEntity = new GameEntity();
		dexGameEntity.setName("Dexterity");
		dexGameEntity.setModify(dexMod);
		dex.setGameEntity(dexGameEntity);

		// Strength modifier
		Modify strengthMod = new Modify();
		strengthMod.setModifyField(ModifyField.STRENGTH_MOD);
		strengthMod.setBase(strengthModAmount);

		GameEntity strengthGameEntity = new GameEntity();
		strengthGameEntity.setName("Strength");
		strengthGameEntity.setModify(strengthMod);
		strength.setGameEntity(strengthGameEntity);

		// Give the dex to the player
		character.addAbilityScore(dex, dexScore);
		character.addAbilityScore(strength, strengthScore);

		// Create Armor
		Armor armor = new Armor();

		// What is armor modified by? Dex in this case
		Modify armorModify = new Modify();
		armorModify.setBase(14);
		armorModify.setModifyField(ModifyField.ARMOR_AC);
		armorModify.setFieldIModify(ModifyField.CHARACTER_AC);

		Map<ModifyField, Integer> fieldsThatModifyMeMap = new HashMap<>();
		fieldsThatModifyMeMap.put(ModifyField.DEXTERITY_MOD, Integer.MAX_VALUE);
		fieldsThatModifyMeMap.put(ModifyField.STRENGTH_MOD, Integer.MAX_VALUE);
		armorModify.setFieldsThatModifyMe(fieldsThatModifyMeMap);

		// Wrap it up together
		GameEntity armorGameEntity = new GameEntity();
		armorGameEntity.setName("Ring Mail");
		armorGameEntity.setModify(armorModify);
		armor.setGameEntity(armorGameEntity);

		// Put armor in character inventory & equip
		character.addItem(armor);
		character.equip(armor);

		// Get Character AC
		int ac = character.getAC();

		int base = armor.getGameEntity().getModifys().stream().mapToInt(modify -> modify.getBase()).sum();
		assertThat(ac, is(base + dexModAmount + strengthModAmount));

		int dexModLimit = 2;
		fieldsThatModifyMeMap.put(ModifyField.DEXTERITY_MOD, dexModLimit);
		ac = character.getAC();
		assertThat(ac, is(base + dexModLimit + strengthModAmount));

		armorModify.setModifyOperation(ModifyOperation.MAX);
		ac = character.getAC();
		assertThat(ac, is(base + Math.max(dexModLimit, strengthModAmount)));
	}

	@Test
	public void weaponFinesseProficiencyTest() {
		// Create Character
		Character character = new Character();

		// Set AbilityScore
		AbilityScore dex = new AbilityScore();
		int dexScore = 20; // 5, want larger so finesse makes us use this one
		int dexModAmount = CharacterAbility.calculateModifier(dexScore);
		AbilityScore strength = new AbilityScore();
		int strengthScore = 16; // 3
		int strengthModAmount = CharacterAbility.calculateModifier(strengthScore);

		// Dex modifier
		Modify dexMod = new Modify();
		dexMod.setModifyField(ModifyField.DEXTERITY_MOD);
		dexMod.setBase(dexModAmount);

		GameEntity dexGameEntity = new GameEntity();
		dexGameEntity.setName("Dexterity");
		dexGameEntity.setModify(dexMod);
		dex.setGameEntity(dexGameEntity);

		// Strength modifier
		Modify strengthMod = new Modify();
		strengthMod.setModifyField(ModifyField.STRENGTH_MOD);
		strengthMod.setBase(strengthModAmount);

		GameEntity strengthGameEntity = new GameEntity();
		strengthGameEntity.setName("Strength");
		strengthGameEntity.setModify(strengthMod);
		strength.setGameEntity(strengthGameEntity);

		// Give the dex to the player
		character.addAbilityScore(dex, dexScore);
		character.addAbilityScore(strength, strengthScore);

		// Set proficiency
		// This should normally be decided by character class -> Leveling Table -> Level
		// -> Modify
		int profBonus = 1;
		Modify profBonusModify = new Modify();
		profBonusModify.setBase(profBonus);
		profBonusModify.setModifyField(ModifyField.PROFICIENCY);
		profBonusModify.setFieldIModify(ModifyField.ATTACK_HIT);

		Level level = new Level();
		level.setProficiencyBonus(profBonusModify);
		level.setLevel(1);

		LevelingTable levelingTable = new LevelingTable();
		levelingTable.setLevels(Arrays.asList(level));

		Classs classs = new Classs();
		classs.setLevelingTable(levelingTable);
		classs.setGameEntity(new GameEntity());

		character.setCharClass(classs);
		character.setCurrentLevel(1);

		// Create weapon
		Weapon weapon = new Weapon();
		GameEntity weaponGameEntity = new GameEntity();
		weaponGameEntity.setName("Sword");
		weapon.setGameEntity(weaponGameEntity);
		weaponGameEntity.addModify(strengthMod);

		// Set weapon magic
		int weaponMagicBonus = 2;
		Modify weaponMagicModify = new Modify();
		weaponMagicModify.setBase(weaponMagicBonus);
		weaponMagicModify.setFieldIModify(ModifyField.ATTACK_HIT);
		weaponMagicModify.setModifyField(ModifyField.WEAPON_MAGIC_BONUS);
		weaponGameEntity.addModify(weaponMagicModify);

		// Set weapon finesse
		Modify finesseModify = new Modify();
		finesseModify.setModifyField(ModifyField.ATTACK_HIT_MOD);
		finesseModify.addFieldThatModifyMe(ModifyField.DEXTERITY_MOD);
		finesseModify.addFieldThatModifyMe(ModifyField.STRENGTH_MOD);
		finesseModify.setModifyOperation(ModifyOperation.MAX);
		weaponGameEntity.addModify(finesseModify);

		// Create ATTACK_HIT_MOD Modify
		Modify attackHitModModify = new Modify();
		attackHitModModify.setFieldIModify(ModifyField.ATTACK_HIT);
		attackHitModModify.setModifyField(ModifyField.ATTACK_HIT_MOD);
		attackHitModModify.setModifyOperation(ModifyOperation.MAX);
		weaponGameEntity.addModify(attackHitModModify);

		// Actually equip weapon
		character.addItem(weapon);
		character.equip(weapon);

		// Check ATTACK_HIT_MOD

		// Check ATTACK_HIT
		int attackHit = character.getAttackHit();
		assertThat(attackHit, is(profBonus + weaponMagicBonus + Math.max(strengthModAmount, dexModAmount)));
	}

	@Test
	public void modifyByLevelTest() {
		// Create Character, level already set
		Character character = new Character();

		// Create class and stuff to not get null pointer exception when calling
		// setCurrentLevel
		Modify profBonusModify1 = new Modify();
		profBonusModify1.setBase(1);
		Level level1 = new Level();
		level1.setProficiencyBonus(profBonusModify1);
		level1.setLevel(1);

		Modify profBonusModify4 = new Modify();
		profBonusModify4.setBase(4);
		Level level4 = new Level();
		level4.setProficiencyBonus(profBonusModify4);
		level4.setLevel(4);

		LevelingTable levelingTable = new LevelingTable();
		levelingTable.setLevels(Arrays.asList(level1, level4));

		Classs classs = new Classs();
		classs.setLevelingTable(levelingTable);
		classs.setGameEntity(new GameEntity());

		character.setCharClass(classs);

		// Create modify based on level
		Modify hillDwarfModify = new Modify();
		hillDwarfModify.setBase(0);
		hillDwarfModify.setModifyField(ModifyField.HP_MOD);
		hillDwarfModify.addFieldThatModifyMe(ModifyField.LEVEL);

		character.addModify(hillDwarfModify);

		int hp = character.getHP();
		assertThat(hp, is(1));

		// change character level and test hp again
		character.setCurrentLevel(4);
		hp = character.getHP();
		assertThat(hp, is(4));
	}
}
